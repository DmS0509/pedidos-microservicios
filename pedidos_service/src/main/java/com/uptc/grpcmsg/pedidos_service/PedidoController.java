package com.uptc.grpcmsg.pedidos_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    private final VerificadorCliente verificadorCliente;
    private final PedidoEventPublisher publisher;

    public PedidoController(VerificadorCliente verificadorCliente, PedidoEventPublisher publisher) {
        this.verificadorCliente = verificadorCliente;
        this.publisher = publisher;
    }

    @PostMapping("/api/pedidos-rabbit")
    public ResponseEntity<String> crearPedidoRabbit(@RequestParam long clienteId, @RequestParam String pedidoId) {
        if (!verificadorCliente.existe(clienteId)) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        publisher.publicarPedidoCreado(pedidoId, "cliente-" + clienteId);
        return ResponseEntity.ok("Pedido " + pedidoId + " creado (RabbitMQ)");
    }


}
