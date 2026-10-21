package com.uptc.grpcmsg.clientes_service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class RepositorioClientes {

    private final Map<Long, String> clientes = Map.of(
            1L, "Jorge Otálora",
            2L, "Laura Gómez"
    );

    public Optional<String> buscarNombre(long id) {
        return Optional.ofNullable(clientes.get(id));
    }
}
