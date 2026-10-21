package com.uptc.grpcmsg.pedidos_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PedidoController {

    private final VerificadorCliente verificadorCliente;
    private final PedidoEventPublisher publisher;
    private final PedidoService pedidoService;

    public PedidoController(VerificadorCliente verificadorCliente, PedidoEventPublisher publisher, PedidoService pedidoService) {
        this.verificadorCliente = verificadorCliente;
        this.publisher = publisher;
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedidos")
    public ResponseEntity<String> crearPedido(@RequestParam long clienteId, @RequestParam String pedidoId) {
        if (!verificadorCliente.existe(clienteId)) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        publisher.publicarPedidoCreado(pedidoId, "cliente-" + clienteId);
        return ResponseEntity.ok("Pedido " + pedidoId + " creado y evento publicado");
    }

    @PostMapping("/pedidos-rabbit")
    public ResponseEntity<String> crearPedidoRabbit(@RequestParam long clienteId, @RequestParam String pedidoId) {
        if (!verificadorCliente.existe(clienteId)) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        publisher.publicarPedidoCreado(pedidoId, "cliente-" + clienteId);
        return ResponseEntity.ok("Pedido " + pedidoId + " creado (RabbitMQ)");
    }

    @PostMapping("/pedidos-outbox")
    public ResponseEntity<String> crearPedidoConOutbox(@RequestParam long clienteId, @RequestParam String pedidoId) {
        if (!verificadorCliente.existe(clienteId)) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        pedidoService.crearPedido(pedidoId, clienteId);
        return ResponseEntity.ok("Pedido " + pedidoId + " guardado; el evento saldra por el outbox");
    }

}
