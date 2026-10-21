package com.uptc.grpcmsg.pedidos_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PruebaGrpcRunner implements CommandLineRunner {

    private final VerificadorCliente verificador;

    PruebaGrpcRunner(VerificadorCliente verificador) {
        this.verificador = verificador;
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> existe(1)  = " + verificador.existe(1));
        System.out.println(">>> existe(99) = " + verificador.existe(99));
    }
}
