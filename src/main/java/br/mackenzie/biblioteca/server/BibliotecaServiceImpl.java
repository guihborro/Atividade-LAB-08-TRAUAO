package br.mackenzie.biblioteca.server;

import br.mackenzie.biblioteca.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementação dos quatro tipos de RPC do BibliotecaService.
 * Persistência em memória com ConcurrentHashMap (thread-safe).
 */
public class BibliotecaServiceImpl extends BibliotecaServiceGrpc.BibliotecaServiceImplBase {

    // ── Acervo em memória ────────────────────────────────────────────────────
    private final Map<String, Livro> acervo = new ConcurrentHashMap<>();
    private final Set<String> isbnsCadastrados = ConcurrentHashMap.newKeySet();
    private final AtomicInteger contadorId = new AtomicInteger(1);

    // ── Sugestões para o chat ─────────────────────────────────────────────────
    private static final Map<String, SugestaoLivro> SUGESTOES = new HashMap<>();

    static {
        SUGESTOES.put("aventura", sugestao("O Senhor dos Anéis", "J.R.R. Tolkien",
                "Épica jornada pela Terra Média cheia de aventuras."));
        SUGESTOES.put("mistério", sugestao("O Nome da Rosa", "Umberto Eco",
                "Investigação num mosteiro medieval repleto de segredos."));
        SUGESTOES.put("ficção", sugestao("Duna", "Frank Herbert",
                "Política, religião e sobrevivência num planeta desértico."));
        SUGESTOES.put("romance", sugestao("Orgulho e Preconceito", "Jane Austen",
                "Clássico sobre amor e sociedade na Inglaterra do século XIX."));
        SUGESTOES.put("terror", sugestao("It — A Coisa", "Stephen King",
                "Grupo de amigos enfrenta um mal ancestral em Derry."));
        SUGESTOES.put("história", sugestao("Sapiens", "Yuval Noah Harari",
                "Breve história da humanidade desde os primórdios."));
        SUGESTOES.put("tecnologia", sugestao("O Programador Apaixonado", "Chad Fowler",
                "Como construir uma carreira notável em software."));
        SUGESTOES.put("filosofia", sugestao("Assim Falou Zaratustra", "Friedrich Nietzsche",
                "Obra central do pensamento nietzschiano."));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 1. UNARY RPC — cadastrarLivro
    // ══════════════════════════════════════════════════════════════════════════
    @Override
    public void cadastrarLivro(CadastrarLivroRequest req,
                               StreamObserver<CadastrarLivroResponse> responseObserver) {

        System.out.printf("[cadastrarLivro] titulo='%s' autor='%s' ano=%d isbn='%s'%n",
                req.getTitulo(), req.getAutor(), req.getAno(), req.getIsbn());

        // Validação básica
        if (req.getTitulo().isBlank() || req.getAutor().isBlank() || req.getIsbn().isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Título, autor e ISBN são obrigatórios.")
                    .asRuntimeException());
            return;
        }

        // ISBN duplicado
        if (isbnsCadastrados.contains(req.getIsbn())) {
            responseObserver.onError(Status.ALREADY_EXISTS
                    .withDescription("Já existe um livro com o ISBN: " + req.getIsbn())
                    .asRuntimeException());
            return;
        }

        // Persiste
        String id = "LIV-" + contadorId.getAndIncrement();
        Livro livro = Livro.newBuilder()
                .setId(id)
                .setTitulo(req.getTitulo())
                .setAutor(req.getAutor())
                .setAno(req.getAno())
                .setIsbn(req.getIsbn())
                .build();

        acervo.put(id, livro);
        isbnsCadastrados.add(req.getIsbn());

        CadastrarLivroResponse resp = CadastrarLivroResponse.newBuilder()
                .setId(id)
                .setSucesso(true)
                .setMensagem("Livro cadastrado com sucesso! ID: " + id)
                .build();

        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 2. SERVER STREAMING RPC — listarLivrosPorAutor
    // ══════════════════════════════════════════════════════════════════════════
    @Override
    public void listarLivrosPorAutor(ListarLivrosPorAutorRequest req,
                                     StreamObserver<Livro> responseObserver) {

        System.out.printf("[listarLivrosPorAutor] autor='%s'%n", req.getAutor());

        List<Livro> encontrados = acervo.values().stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(req.getAutor()))
                .toList();

        if (encontrados.isEmpty()) {
            // Retorna NOT_FOUND quando o autor não tem livros cadastrados
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Nenhum livro encontrado para o autor: " + req.getAutor())
                    .asRuntimeException());
            return;
        }

        // Envia cada livro individualmente no stream
        for (Livro livro : encontrados) {
            System.out.printf("  → enviando: '%s'%n", livro.getTitulo());
            responseObserver.onNext(livro);
        }
        responseObserver.onCompleted();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 3. CLIENT STREAMING RPC — registrarEmprestimos
    // ══════════════════════════════════════════════════════════════════════════
    @Override
    public StreamObserver<EmprestimoRequest> registrarEmprestimos(
            StreamObserver<ResumoEmprestimos> responseObserver) {

        System.out.println("[registrarEmprestimos] stream iniciado");

        return new StreamObserver<>() {
            private int totalRegistrados = 0;
            private final long inicio = System.currentTimeMillis();

            @Override
            public void onNext(EmprestimoRequest req) {
                System.out.printf("  → empréstimo: usuario='%s' livro_id='%s'%n",
                        req.getUsuario(), req.getLivroId());

                // Valida se o livro existe
                if (!acervo.containsKey(req.getLivroId())) {
                    System.out.printf("     [AVISO] livro_id='%s' não encontrado, ignorado.%n",
                            req.getLivroId());
                } else {
                    totalRegistrados++;
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("[registrarEmprestimos] erro no stream: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                long tempoMs = System.currentTimeMillis() - inicio;

                ResumoEmprestimos resumo = ResumoEmprestimos.newBuilder()
                        .setTotalRegistrados(totalRegistrados)
                        .setTempoProcessamentoMs(tempoMs)
                        .setMensagem(totalRegistrados + " empréstimo(s) registrado(s) em " + tempoMs + " ms.")
                        .build();

                System.out.printf("[registrarEmprestimos] finalizado — total=%d tempo=%dms%n",
                        totalRegistrados, tempoMs);

                responseObserver.onNext(resumo);
                responseObserver.onCompleted();
            }
        };
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 4. BIDIRECTIONAL STREAMING RPC — chatBibliotecario
    // ══════════════════════════════════════════════════════════════════════════
    @Override
    public StreamObserver<MensagemUsuario> chatBibliotecario(
            StreamObserver<SugestaoLivro> responseObserver) {

        System.out.println("[chatBibliotecario] sessão iniciada");

        return new StreamObserver<>() {

            @Override
            public void onNext(MensagemUsuario msg) {
                System.out.printf("  → mensagem de '%s': '%s'%n",
                        msg.getUsuario(), msg.getMensagem());

                // Procura palavra-chave (case-insensitive) na mensagem
                String chave = encontrarChave(msg.getMensagem().toLowerCase());

                SugestaoLivro sugestao = SUGESTOES.getOrDefault(chave,
                        sugestao("1984", "George Orwell",
                                "Sugestão padrão: distopia clássica sobre vigilância e poder."));

                responseObserver.onNext(sugestao);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("[chatBibliotecario] erro: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("[chatBibliotecario] sessão encerrada");
                responseObserver.onCompleted();
            }
        };
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** Procura a primeira palavra-chave conhecida dentro do texto. */
    private String encontrarChave(String texto) {
        for (String chave : SUGESTOES.keySet()) {
            if (texto.contains(chave)) return chave;
        }
        return "__default__"; // nenhuma chave encontrada → sugestão padrão
    }

    private static SugestaoLivro sugestao(String titulo, String autor, String desc) {
        return SugestaoLivro.newBuilder()
                .setTitulo(titulo)
                .setAutor(autor)
                .setDescricao(desc)
                .build();
    }
}
