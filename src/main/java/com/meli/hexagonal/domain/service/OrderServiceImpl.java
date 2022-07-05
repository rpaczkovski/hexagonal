package com.meli.hexagonal.domain.service;

import com.meli.hexagonal.domain.Order;
import com.meli.hexagonal.domain.Product;
import com.meli.hexagonal.domain.service.ports.repository.OrderReaderPort;
import com.meli.hexagonal.domain.service.ports.repository.OrderWriterPort;
import com.meli.hexagonal.domain.service.ports.service.OrderServicePort;
import com.meli.hexagonal.domain.valueObject.CustomId;

public class OrderServiceImpl implements OrderServicePort {

    private OrderReaderPort orderReaderRepository;
    private OrderWriterPort orderWriterRepository;

    @Override
    public Long createOrder(Product product) {
        Order order = new Order(CustomId.randomId(),product);
        orderWriterRepository.save(order);
        return order.getId();
    }

    @Override
    public void addProduct(Long id, Product product) {
        Order order = getOrder(id);
        order.addOrder(product);
        orderWriterRepository.save(order);
    }

    @Override
    public void completeOrder(Long id) {
        Order order = getOrder(id);
        order.complete();
        orderWriterRepository.save(order);
    }

    @Override
    public void deleteProduct(Long id, Long productId) {
        Order order = getOrder(id);
        order.removeOrder(productId);
        orderWriterRepository.save(order);
    }

    private Order getOrder(Long id) {
        CustomId customId = new CustomId(id);
        return orderReaderRepository
                .findById(customId.getId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("The order with the id %n was not found.", id)));
    }
}
