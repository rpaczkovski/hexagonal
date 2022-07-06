package com.meli.hexagonal.domain.ports.repository;

import com.meli.hexagonal.domain.Order;

import java.util.Optional;

public interface OrderRepositoryPort {
    Optional<Order> findById(Long id);
    void save(Order order);
}
