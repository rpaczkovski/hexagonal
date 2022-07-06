package com.meli.hexagonal.domain.service;

import com.meli.hexagonal.domain.Order;
import com.meli.hexagonal.domain.Product;
import com.meli.hexagonal.domain.ports.repository.OrderRepositoryPort;
import com.meli.hexagonal.domain.ports.service.OrderServicePort;
import com.meli.hexagonal.domain.valueObject.CustomId;

public class OrderServiceImpl implements OrderServicePort {

    private OrderRepositoryPort orderRepositoryPort;

    public OrderServiceImpl(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Long createOrder(Product product) {
        Order order = new Order(CustomId.randomId(),product);
        orderRepositoryPort.save(order);
        return order.getId();
    }

    @Override
    public void addProduct(Long id, Product product) {
        Order order = getOrder(id);
        order.addOrder(product);
        orderRepositoryPort.save(order);
    }

    @Override
    public void completeOrder(Long id) {
        Order order = getOrder(id);
        order.complete();
        orderRepositoryPort.save(order);
    }

    @Override
    public void deleteProduct(Long id, Long productId) {
        Order order = getOrder(id);
        order.removeOrder(productId);
        orderRepositoryPort.save(order);
    }

    private Order getOrder(Long id) {
        CustomId customId = new CustomId(id);
        return orderRepositoryPort
                .findById(customId.getId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("The order with the id %n was not found.", id)));
    }
}
