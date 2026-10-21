package com.uptc.grpcmsg.pedidos_service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PedidoEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarPedidoCreado(String pedidoId, String clienteNombre) {
        String payload = """
                {"pedidoId": "%s", "cliente": "%s"}""".formatted(pedidoId, clienteNombre);
        kafkaTemplate.send("pedidos.creados", pedidoId, payload);
    }

}
