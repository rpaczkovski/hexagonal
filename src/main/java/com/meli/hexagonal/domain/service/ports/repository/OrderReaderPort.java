package com.meli.hexagonal.domain.service.ports.repository;

import com.meli.hexagonal.domain.Order;

import java.util.Optional;

public interface OrderReaderPort {
    Optional<Order> findById(Long id);
}
