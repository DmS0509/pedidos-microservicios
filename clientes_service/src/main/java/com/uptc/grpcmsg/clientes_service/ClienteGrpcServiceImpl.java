package com.uptc.grpcmsg.clientes_service;

import org.springframework.grpc.server.service.GrpcService;

import com.uptc.grpcmsg.clientes_service.grpc.ClienteGrpcGrpc;
import com.uptc.grpcmsg.clientes_service.grpc.ClienteRequest;
import com.uptc.grpcmsg.clientes_service.grpc.ClienteResponse;

import io.grpc.stub.StreamObserver;

@GrpcService
public class ClienteGrpcServiceImpl extends ClienteGrpcGrpc.ClienteGrpcImplBase {

    private final RepositorioClientes repositorio;

    public ClienteGrpcServiceImpl(RepositorioClientes repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void existeCliente(ClienteRequest request, StreamObserver<ClienteResponse> responseObserver) {
        var nombre = repositorio.buscarNombre(request.getId());
        var respuesta = ClienteResponse.newBuilder()
                .setExiste(nombre.isPresent())
                .setNombreCompleto(nombre.orElse(""))
                .build();
        responseObserver.onNext(respuesta);
        responseObserver.onCompleted();
    }
}
