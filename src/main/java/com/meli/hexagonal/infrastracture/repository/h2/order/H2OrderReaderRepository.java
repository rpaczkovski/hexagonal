package com.meli.hexagonal.infrastracture.repository.h2.order;

import com.meli.hexagonal.domain.Order;
import com.meli.hexagonal.domain.ports.repository.OrderRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class H2OrderReaderRepository implements OrderRepositoryPort {

    private final SpringDataH2OrderRepository orderRepository;

    @Autowired
    public H2OrderReaderRepository(SpringDataH2OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);

        if(orderEntity.isPresent()) {
            return Optional.of(orderEntity.get().toOrder());
        }

        return Optional.empty();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(new OrderEntity(order));
    }
}
