package com.meli.hexagonal.infrastracture.repository.h2.order;

import com.meli.hexagonal.domain.Order;
import com.meli.hexagonal.domain.OrderItem;
import com.meli.hexagonal.domain.OrderStatus;
import com.meli.hexagonal.domain.Product;
import com.meli.hexagonal.domain.valueObject.CustomId;
import com.meli.hexagonal.domain.valueObject.Money;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class OrderEntity {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    private List<OrderItemEntity> orderItemEntities;
    private BigDecimal price;

    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.price = order.getTotalPrice();
        this.status = order.getStatus();
    }

    public Order toOrder() {
        List<OrderItem> orderItems = orderItemEntities.stream()
                .map(OrderItemEntity::toOrderItem)
                .collect(Collectors.toList());

        List<Product> products = orderItems.stream()
                .map(orderItem -> new Product(new CustomId(orderItem.getProductId()), new Money(orderItem.getPrice()), ""))
                .collect(Collectors.toList());

        Order order = new Order(new CustomId(id), products.remove(0));

        products.forEach(product -> order.addOrder(product));

        if(status == OrderStatus.COMPLETED) {
            order.complete();
        }

        return order;

    }

}
