package com.uptc.grpcmsg.pedidos_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventPublisherRabbit {

    private final RabbitTemplate rabbitTemplate;

    public PedidoEventPublisherRabbit(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarPedidoCreado(String pedidoId, String clienteNombre) {
        String payload = """
            {"pedidoId": "%s", "cliente": "%s"}""".formatted(pedidoId, clienteNombre);
        rabbitTemplate.convertAndSend("pedidos-exchange", "pedidos.creados", payload);
    }
}
