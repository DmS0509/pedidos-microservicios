package com.uptc.grpcmsg.pedidos_service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {

    List<OutboxEvent> findByPublicadoFalseOrderByIdAsc();
}
