package com.meli.hexagonal.domain.service.ports.repository;

import com.meli.hexagonal.domain.Order;

public interface OrderWriterPort {
    void save(Order order);
}
