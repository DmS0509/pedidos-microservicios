package com.uptc.grpcmsg.pedidos_service;

import org.springframework.stereotype.Component;

import com.uptc.grpcmsg.pedidos_service.grpc.ClienteGrpcGrpc.ClienteGrpcBlockingStub;
import com.uptc.grpcmsg.pedidos_service.grpc.ClienteRequest;

@Component
public class VerificadorCliente {

    private final ClienteGrpcBlockingStub stub;

    public VerificadorCliente(ClienteGrpcBlockingStub stub) {
        this.stub = stub;
    }

    public boolean existe(long clienteId) {
        var request = ClienteRequest.newBuilder().setId(clienteId).build();
        return stub.existeCliente(request).getExiste();
    }

}
