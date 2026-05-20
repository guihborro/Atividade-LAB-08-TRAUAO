package br.mackenzie.biblioteca;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.0)",
    comments = "Source: biblioteca.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BibliotecaServiceGrpc {

  private BibliotecaServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "biblioteca.BibliotecaService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<br.mackenzie.biblioteca.CadastrarLivroRequest,
      br.mackenzie.biblioteca.CadastrarLivroResponse> getCadastrarLivroMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cadastrarLivro",
      requestType = br.mackenzie.biblioteca.CadastrarLivroRequest.class,
      responseType = br.mackenzie.biblioteca.CadastrarLivroResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.mackenzie.biblioteca.CadastrarLivroRequest,
      br.mackenzie.biblioteca.CadastrarLivroResponse> getCadastrarLivroMethod() {
    io.grpc.MethodDescriptor<br.mackenzie.biblioteca.CadastrarLivroRequest, br.mackenzie.biblioteca.CadastrarLivroResponse> getCadastrarLivroMethod;
    if ((getCadastrarLivroMethod = BibliotecaServiceGrpc.getCadastrarLivroMethod) == null) {
      synchronized (BibliotecaServiceGrpc.class) {
        if ((getCadastrarLivroMethod = BibliotecaServiceGrpc.getCadastrarLivroMethod) == null) {
          BibliotecaServiceGrpc.getCadastrarLivroMethod = getCadastrarLivroMethod =
              io.grpc.MethodDescriptor.<br.mackenzie.biblioteca.CadastrarLivroRequest, br.mackenzie.biblioteca.CadastrarLivroResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cadastrarLivro"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.CadastrarLivroRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.CadastrarLivroResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BibliotecaServiceMethodDescriptorSupplier("cadastrarLivro"))
              .build();
        }
      }
    }
    return getCadastrarLivroMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.mackenzie.biblioteca.ListarLivrosPorAutorRequest,
      br.mackenzie.biblioteca.Livro> getListarLivrosPorAutorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listarLivrosPorAutor",
      requestType = br.mackenzie.biblioteca.ListarLivrosPorAutorRequest.class,
      responseType = br.mackenzie.biblioteca.Livro.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<br.mackenzie.biblioteca.ListarLivrosPorAutorRequest,
      br.mackenzie.biblioteca.Livro> getListarLivrosPorAutorMethod() {
    io.grpc.MethodDescriptor<br.mackenzie.biblioteca.ListarLivrosPorAutorRequest, br.mackenzie.biblioteca.Livro> getListarLivrosPorAutorMethod;
    if ((getListarLivrosPorAutorMethod = BibliotecaServiceGrpc.getListarLivrosPorAutorMethod) == null) {
      synchronized (BibliotecaServiceGrpc.class) {
        if ((getListarLivrosPorAutorMethod = BibliotecaServiceGrpc.getListarLivrosPorAutorMethod) == null) {
          BibliotecaServiceGrpc.getListarLivrosPorAutorMethod = getListarLivrosPorAutorMethod =
              io.grpc.MethodDescriptor.<br.mackenzie.biblioteca.ListarLivrosPorAutorRequest, br.mackenzie.biblioteca.Livro>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listarLivrosPorAutor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.ListarLivrosPorAutorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.Livro.getDefaultInstance()))
              .setSchemaDescriptor(new BibliotecaServiceMethodDescriptorSupplier("listarLivrosPorAutor"))
              .build();
        }
      }
    }
    return getListarLivrosPorAutorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.mackenzie.biblioteca.EmprestimoRequest,
      br.mackenzie.biblioteca.ResumoEmprestimos> getRegistrarEmprestimosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registrarEmprestimos",
      requestType = br.mackenzie.biblioteca.EmprestimoRequest.class,
      responseType = br.mackenzie.biblioteca.ResumoEmprestimos.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<br.mackenzie.biblioteca.EmprestimoRequest,
      br.mackenzie.biblioteca.ResumoEmprestimos> getRegistrarEmprestimosMethod() {
    io.grpc.MethodDescriptor<br.mackenzie.biblioteca.EmprestimoRequest, br.mackenzie.biblioteca.ResumoEmprestimos> getRegistrarEmprestimosMethod;
    if ((getRegistrarEmprestimosMethod = BibliotecaServiceGrpc.getRegistrarEmprestimosMethod) == null) {
      synchronized (BibliotecaServiceGrpc.class) {
        if ((getRegistrarEmprestimosMethod = BibliotecaServiceGrpc.getRegistrarEmprestimosMethod) == null) {
          BibliotecaServiceGrpc.getRegistrarEmprestimosMethod = getRegistrarEmprestimosMethod =
              io.grpc.MethodDescriptor.<br.mackenzie.biblioteca.EmprestimoRequest, br.mackenzie.biblioteca.ResumoEmprestimos>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registrarEmprestimos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.EmprestimoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.ResumoEmprestimos.getDefaultInstance()))
              .setSchemaDescriptor(new BibliotecaServiceMethodDescriptorSupplier("registrarEmprestimos"))
              .build();
        }
      }
    }
    return getRegistrarEmprestimosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.mackenzie.biblioteca.MensagemUsuario,
      br.mackenzie.biblioteca.SugestaoLivro> getChatBibliotecarioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "chatBibliotecario",
      requestType = br.mackenzie.biblioteca.MensagemUsuario.class,
      responseType = br.mackenzie.biblioteca.SugestaoLivro.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<br.mackenzie.biblioteca.MensagemUsuario,
      br.mackenzie.biblioteca.SugestaoLivro> getChatBibliotecarioMethod() {
    io.grpc.MethodDescriptor<br.mackenzie.biblioteca.MensagemUsuario, br.mackenzie.biblioteca.SugestaoLivro> getChatBibliotecarioMethod;
    if ((getChatBibliotecarioMethod = BibliotecaServiceGrpc.getChatBibliotecarioMethod) == null) {
      synchronized (BibliotecaServiceGrpc.class) {
        if ((getChatBibliotecarioMethod = BibliotecaServiceGrpc.getChatBibliotecarioMethod) == null) {
          BibliotecaServiceGrpc.getChatBibliotecarioMethod = getChatBibliotecarioMethod =
              io.grpc.MethodDescriptor.<br.mackenzie.biblioteca.MensagemUsuario, br.mackenzie.biblioteca.SugestaoLivro>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "chatBibliotecario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.MensagemUsuario.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.mackenzie.biblioteca.SugestaoLivro.getDefaultInstance()))
              .setSchemaDescriptor(new BibliotecaServiceMethodDescriptorSupplier("chatBibliotecario"))
              .build();
        }
      }
    }
    return getChatBibliotecarioMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BibliotecaServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BibliotecaServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BibliotecaServiceStub>() {
        @java.lang.Override
        public BibliotecaServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BibliotecaServiceStub(channel, callOptions);
        }
      };
    return BibliotecaServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BibliotecaServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BibliotecaServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BibliotecaServiceBlockingStub>() {
        @java.lang.Override
        public BibliotecaServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BibliotecaServiceBlockingStub(channel, callOptions);
        }
      };
    return BibliotecaServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BibliotecaServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BibliotecaServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BibliotecaServiceFutureStub>() {
        @java.lang.Override
        public BibliotecaServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BibliotecaServiceFutureStub(channel, callOptions);
        }
      };
    return BibliotecaServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * 1. Unary RPC
     * </pre>
     */
    default void cadastrarLivro(br.mackenzie.biblioteca.CadastrarLivroRequest request,
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.CadastrarLivroResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCadastrarLivroMethod(), responseObserver);
    }

    /**
     * <pre>
     * 2. Server Streaming RPC
     * </pre>
     */
    default void listarLivrosPorAutor(br.mackenzie.biblioteca.ListarLivrosPorAutorRequest request,
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.Livro> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListarLivrosPorAutorMethod(), responseObserver);
    }

    /**
     * <pre>
     * 3. Client Streaming RPC
     * </pre>
     */
    default io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.EmprestimoRequest> registrarEmprestimos(
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.ResumoEmprestimos> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getRegistrarEmprestimosMethod(), responseObserver);
    }

    /**
     * <pre>
     * 4. Bidirectional Streaming RPC
     * </pre>
     */
    default io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.MensagemUsuario> chatBibliotecario(
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.SugestaoLivro> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getChatBibliotecarioMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BibliotecaService.
   */
  public static abstract class BibliotecaServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BibliotecaServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BibliotecaService.
   */
  public static final class BibliotecaServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BibliotecaServiceStub> {
    private BibliotecaServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BibliotecaServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BibliotecaServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. Unary RPC
     * </pre>
     */
    public void cadastrarLivro(br.mackenzie.biblioteca.CadastrarLivroRequest request,
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.CadastrarLivroResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCadastrarLivroMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 2. Server Streaming RPC
     * </pre>
     */
    public void listarLivrosPorAutor(br.mackenzie.biblioteca.ListarLivrosPorAutorRequest request,
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.Livro> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListarLivrosPorAutorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 3. Client Streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.EmprestimoRequest> registrarEmprestimos(
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.ResumoEmprestimos> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getRegistrarEmprestimosMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 4. Bidirectional Streaming RPC
     * </pre>
     */
    public io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.MensagemUsuario> chatBibliotecario(
        io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.SugestaoLivro> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getChatBibliotecarioMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BibliotecaService.
   */
  public static final class BibliotecaServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BibliotecaServiceBlockingStub> {
    private BibliotecaServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BibliotecaServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BibliotecaServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. Unary RPC
     * </pre>
     */
    public br.mackenzie.biblioteca.CadastrarLivroResponse cadastrarLivro(br.mackenzie.biblioteca.CadastrarLivroRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCadastrarLivroMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 2. Server Streaming RPC
     * </pre>
     */
    public java.util.Iterator<br.mackenzie.biblioteca.Livro> listarLivrosPorAutor(
        br.mackenzie.biblioteca.ListarLivrosPorAutorRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListarLivrosPorAutorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BibliotecaService.
   */
  public static final class BibliotecaServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BibliotecaServiceFutureStub> {
    private BibliotecaServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BibliotecaServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BibliotecaServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. Unary RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<br.mackenzie.biblioteca.CadastrarLivroResponse> cadastrarLivro(
        br.mackenzie.biblioteca.CadastrarLivroRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCadastrarLivroMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CADASTRAR_LIVRO = 0;
  private static final int METHODID_LISTAR_LIVROS_POR_AUTOR = 1;
  private static final int METHODID_REGISTRAR_EMPRESTIMOS = 2;
  private static final int METHODID_CHAT_BIBLIOTECARIO = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CADASTRAR_LIVRO:
          serviceImpl.cadastrarLivro((br.mackenzie.biblioteca.CadastrarLivroRequest) request,
              (io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.CadastrarLivroResponse>) responseObserver);
          break;
        case METHODID_LISTAR_LIVROS_POR_AUTOR:
          serviceImpl.listarLivrosPorAutor((br.mackenzie.biblioteca.ListarLivrosPorAutorRequest) request,
              (io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.Livro>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTRAR_EMPRESTIMOS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.registrarEmprestimos(
              (io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.ResumoEmprestimos>) responseObserver);
        case METHODID_CHAT_BIBLIOTECARIO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chatBibliotecario(
              (io.grpc.stub.StreamObserver<br.mackenzie.biblioteca.SugestaoLivro>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCadastrarLivroMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              br.mackenzie.biblioteca.CadastrarLivroRequest,
              br.mackenzie.biblioteca.CadastrarLivroResponse>(
                service, METHODID_CADASTRAR_LIVRO)))
        .addMethod(
          getListarLivrosPorAutorMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              br.mackenzie.biblioteca.ListarLivrosPorAutorRequest,
              br.mackenzie.biblioteca.Livro>(
                service, METHODID_LISTAR_LIVROS_POR_AUTOR)))
        .addMethod(
          getRegistrarEmprestimosMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              br.mackenzie.biblioteca.EmprestimoRequest,
              br.mackenzie.biblioteca.ResumoEmprestimos>(
                service, METHODID_REGISTRAR_EMPRESTIMOS)))
        .addMethod(
          getChatBibliotecarioMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              br.mackenzie.biblioteca.MensagemUsuario,
              br.mackenzie.biblioteca.SugestaoLivro>(
                service, METHODID_CHAT_BIBLIOTECARIO)))
        .build();
  }

  private static abstract class BibliotecaServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BibliotecaServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.mackenzie.biblioteca.BibliotecaProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BibliotecaService");
    }
  }

  private static final class BibliotecaServiceFileDescriptorSupplier
      extends BibliotecaServiceBaseDescriptorSupplier {
    BibliotecaServiceFileDescriptorSupplier() {}
  }

  private static final class BibliotecaServiceMethodDescriptorSupplier
      extends BibliotecaServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BibliotecaServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BibliotecaServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BibliotecaServiceFileDescriptorSupplier())
              .addMethod(getCadastrarLivroMethod())
              .addMethod(getListarLivrosPorAutorMethod())
              .addMethod(getRegistrarEmprestimosMethod())
              .addMethod(getChatBibliotecarioMethod())
              .build();
        }
      }
    }
    return result;
  }
}
