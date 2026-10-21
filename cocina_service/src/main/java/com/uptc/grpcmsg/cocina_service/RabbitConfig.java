package com.uptc.grpcmsg.cocina_service;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue pedidosCreadosQueue() {
        return new Queue("pedidos.creados.queue");
    }
}
