package com.meli.hexagonal.domain.ports.service;

import com.meli.hexagonal.domain.Product;

public interface OrderServicePort {
    Long createOrder(Product product);

    void addProduct(Long id, Product product);

    void completeOrder(Long id);

    void deleteProduct(Long id, Long productId);
}
