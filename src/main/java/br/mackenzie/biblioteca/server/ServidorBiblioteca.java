package br.mackenzie.biblioteca.server;

import io.grpc.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Inicializa e mantém o servidor gRPC na porta 50051.
 * Inclui um ServerInterceptor de log automático (bônus).
 */
public class ServidorBiblioteca {

    private static final int PORTA = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {

        // Interceptor de log automático (bônus)
        ServerInterceptor logInterceptor = new ServerInterceptor() {
            @Override
            public <Req, Resp> ServerCall.Listener<Req> interceptCall(
                    ServerCall<Req, Resp> call,
                    Metadata headers,
                    ServerCallHandler<Req, Resp> next) {

                // Log do token de autenticação simples (bônus autenticação)
                String token = headers.get(
                        Metadata.Key.of("auth-token", Metadata.ASCII_STRING_MARSHALLER));

                if (token != null) {
                    System.out.printf("[Interceptor] Auth-Token recebido: '%s'%n", token);
                    if (!token.equals("biblioteca-secret-2024")) {
                        call.close(Status.UNAUTHENTICATED
                                        .withDescription("Token inválido."),
                                new Metadata());
                        return new ServerCall.Listener<>() {};
                    }
                }

                System.out.printf("[Interceptor] Chamada recebida: %s%n",
                        call.getMethodDescriptor().getFullMethodName());

                return next.startCall(call, headers);
            }
        };

        Server servidor = ServerBuilder.forPort(PORTA)
                .addService(ServerInterceptors.intercept(
                        new BibliotecaServiceImpl(), logInterceptor))
                .build()
                .start();

        System.out.println("═══════════════════════════════════════════");
        System.out.println("  Servidor Biblioteca gRPC iniciado!");
        System.out.println("  Porta: " + PORTA);
        System.out.println("═══════════════════════════════════════════");

        // Hook para desligar o servidor graciosamente com Ctrl+C
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n[Servidor] Encerrando...");
            servidor.shutdown();
            try {
                if (!servidor.awaitTermination(5, TimeUnit.SECONDS)) {
                    servidor.shutdownNow();
                }
            } catch (InterruptedException e) {
                servidor.shutdownNow();
            }
            System.out.println("[Servidor] Encerrado.");
        }));

        servidor.awaitTermination();
    }
}
