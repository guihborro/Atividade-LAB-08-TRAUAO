package br.mackenzie.biblioteca.client;

import br.mackenzie.biblioteca.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;

/**
 * Cliente que demonstra todos os 4 tipos de RPC conforme o roteiro de testes.
 * Inclui deadline/timeout (bônus) e envio de auth-token (bônus).
 */
public class ClienteBiblioteca {

    private static final String HOST = "localhost";
    private static final int    PORTA = 50051;

    // Token para autenticação simples via metadata (bônus)
    private static final String AUTH_TOKEN = "biblioteca-secret-2024";

    public static void main(String[] args) throws InterruptedException {

        // Cria canal gRPC
        ManagedChannel canal = ManagedChannelBuilder
                .forAddress(HOST, PORTA)
                .usePlaintext()
                .build();

        // Stubs: blocking para chamadas síncronas, async para streaming
        BibliotecaServiceGrpc.BibliotecaServiceBlockingStub  stubBlock =
                BibliotecaServiceGrpc.newBlockingStub(canal);
        BibliotecaServiceGrpc.BibliotecaServiceStub stubAsync =
                BibliotecaServiceGrpc.newStub(canal);

        // Adiciona o auth-token via CallCredentials/Metadata em cada stub
        stubBlock = stubBlock.withCallCredentials(credenciais());
        stubAsync = stubAsync.withCallCredentials(credenciais());

        try {
            separador("TESTE 1 — Unary: Cadastrar 3 livros");
            cadastrarLivros(stubBlock);

            separador("TESTE 2 — Server Streaming: Listar livros por autor existente");
            listarPorAutor(stubBlock, "George Orwell");

            separador("TESTE 3 — Server Streaming: Autor inexistente (espera NOT_FOUND)");
            listarPorAutor(stubBlock, "Autor Inexistente");

            separador("TESTE 4 — Client Streaming: Registrar 5 empréstimos");
            registrarEmprestimos(stubAsync);

            separador("TESTE 5 — Bidirectional Streaming: Chat com 3 mensagens");
            chat(stubAsync);

            separador("TESTE 6 — Unary: ISBN duplicado (espera ALREADY_EXISTS)");
            cadastrarIsbnDuplicado(stubBlock);

        } finally {
            canal.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("\n[Cliente] Canal encerrado.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 1. Unary — cadastrar livros
    // ══════════════════════════════════════════════════════════════════════════
    private static void cadastrarLivros(
            BibliotecaServiceGrpc.BibliotecaServiceBlockingStub stub) {

        String[][] livros = {
            {"1984",                         "George Orwell",     "1949", "978-0451524935"},
            {"A Revolução dos Bichos",        "George Orwell",     "1945", "978-0451526342"},
            {"O Senhor dos Anéis",            "J.R.R. Tolkien",    "1954", "978-0618640157"},
        };

        for (String[] l : livros) {
            try {
                // Deadline de 3 segundos (bônus)
                CadastrarLivroResponse resp = stub
                        .withDeadlineAfter(3, TimeUnit.SECONDS)
                        .cadastrarLivro(CadastrarLivroRequest.newBuilder()
                                .setTitulo(l[0])
                                .setAutor(l[1])
                                .setAno(Integer.parseInt(l[2]))
                                .setIsbn(l[3])
                                .build());

                System.out.printf("  ✓ %s → %s%n", l[0], resp.getMensagem());

            } catch (StatusRuntimeException e) {
                System.err.printf("  ✗ Erro ao cadastrar '%s': [%s] %s%n",
                        l[0], e.getStatus().getCode(), e.getStatus().getDescription());
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 2. Server Streaming — listar por autor
    // ══════════════════════════════════════════════════════════════════════════
    private static void listarPorAutor(
            BibliotecaServiceGrpc.BibliotecaServiceBlockingStub stub,
            String autor) {

        try {
            Iterator<Livro> stream = stub
                    .withDeadlineAfter(5, TimeUnit.SECONDS)
                    .listarLivrosPorAutor(
                            ListarLivrosPorAutorRequest.newBuilder()
                                    .setAutor(autor)
                                    .build());

            System.out.printf("  Livros de '%s':%n", autor);
            while (stream.hasNext()) {
                Livro l = stream.next();
                System.out.printf("    [%s] %s (%d) ISBN:%s%n",
                        l.getId(), l.getTitulo(), l.getAno(), l.getIsbn());
            }

        } catch (StatusRuntimeException e) {
            System.err.printf("  ✗ [%s] %s%n",
                    e.getStatus().getCode(), e.getStatus().getDescription());
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 3. Client Streaming — registrar empréstimos
    // ══════════════════════════════════════════════════════════════════════════
    private static void registrarEmprestimos(
            BibliotecaServiceGrpc.BibliotecaServiceStub stub) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<EmprestimoRequest> requestStream =
                stub.withDeadlineAfter(10, TimeUnit.SECONDS)
                        .registrarEmprestimos(new StreamObserver<>() {

            @Override
            public void onNext(ResumoEmprestimos resumo) {
                System.out.printf("  ✓ Resumo: %s%n", resumo.getMensagem());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("  ✗ Erro: " + t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("  ✓ Stream de empréstimos concluído.");
                latch.countDown();
            }
        });

        // 5 empréstimos (LIV-1 a LIV-3 existem; LIV-99 não existe)
        String[][] emprestimos = {
            {"Ana Silva",    "LIV-1"},
            {"Bruno Costa",  "LIV-2"},
            {"Carla Dias",   "LIV-3"},
            {"Daniel Melo",  "LIV-1"},
            {"Eva Santos",   "LIV-99"}, // livro inexistente → ignorado
        };

        for (String[] e : emprestimos) {
            requestStream.onNext(EmprestimoRequest.newBuilder()
                    .setUsuario(e[0])
                    .setLivroId(e[1])
                    .build());
            System.out.printf("  → enviado: usuario='%s' livro='%s'%n", e[0], e[1]);
        }

        requestStream.onCompleted();
        latch.await(15, TimeUnit.SECONDS);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 4. Bidirectional Streaming — chat
    // ══════════════════════════════════════════════════════════════════════════
    private static void chat(
            BibliotecaServiceGrpc.BibliotecaServiceStub stub) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<MensagemUsuario> requestStream =
                stub.withDeadlineAfter(10, TimeUnit.SECONDS)
                        .chatBibliotecario(new StreamObserver<>() {

            @Override
            public void onNext(SugestaoLivro sugestao) {
                System.out.printf("  📚 Sugestão: \"%s\" — %s%n  ℹ  %s%n",
                        sugestao.getTitulo(), sugestao.getAutor(), sugestao.getDescricao());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("  ✗ Erro no chat: " + t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("  ✓ Chat encerrado.");
                latch.countDown();
            }
        });

        // 3 mensagens conforme o roteiro
        String[][] mensagens = {
            {"João",  "Gosto de ficção científica, alguma sugestão?"},
            {"João",  "Tenho interesse em aventura também"},
            {"João",  "E que tal algo sobre filosofia?"},
        };

        for (String[] m : mensagens) {
            System.out.printf("  👤 %s: %s%n", m[0], m[1]);
            requestStream.onNext(MensagemUsuario.newBuilder()
                    .setUsuario(m[0])
                    .setMensagem(m[1])
                    .build());
            Thread.sleep(300); // Pausa para simular digitação
        }

        requestStream.onCompleted();
        latch.await(15, TimeUnit.SECONDS);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // 6. Teste ISBN duplicado
    // ══════════════════════════════════════════════════════════════════════════
    private static void cadastrarIsbnDuplicado(
            BibliotecaServiceGrpc.BibliotecaServiceBlockingStub stub) {

        try {
            stub.withDeadlineAfter(3, TimeUnit.SECONDS)
                    .cadastrarLivro(CadastrarLivroRequest.newBuilder()
                            .setTitulo("1984 — Edição Especial")
                            .setAutor("George Orwell")
                            .setAno(2024)
                            .setIsbn("978-0451524935") // mesmo ISBN do primeiro cadastro
                            .build());

        } catch (StatusRuntimeException e) {
            System.err.printf("  ✗ [%s] %s%n",
                    e.getStatus().getCode(), e.getStatus().getDescription());
        }
    }

    // ── Helper: CallCredentials com auth-token simples (bônus) ───────────────
    private static CallCredentials credenciais() {
        return new CallCredentials() {
            @Override
            public void applyRequestMetadata(RequestInfo ri,
                                             java.util.concurrent.Executor exec,
                                             MetadataApplier applier) {
                exec.execute(() -> {
                    Metadata meta = new Metadata();
                    meta.put(Metadata.Key.of("auth-token", Metadata.ASCII_STRING_MARSHALLER),
                             AUTH_TOKEN);
                    applier.apply(meta);
                });
            }
        };
    }

    // ── Helper: Separador visual ──────────────────────────────────────────────
    private static void separador(String titulo) {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║  " + titulo);
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
