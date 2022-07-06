package com.meli.hexagonal.domain;

import com.meli.hexagonal.domain.valueObject.CustomId;

import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;

public class Order {
    private CustomId id;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal totalPrice;

    private Order() {
    }

    public Order(CustomId id, final Product product) {
        this.id = id;
        addOrder(product);
    }

    public void complete() {
        validateStatus();
        status = OrderStatus.COMPLETED;
    }

    public void addOrder(Product product) {
        validateStatus();
        validateProduct(product);

        orderItems.add(new OrderItem(product));
        totalPrice = totalPrice.add(product.getPrice());
    }

    public void removeOrder(Long id) {
        validateStatus();
        OrderItem orderItem = getOrderItem(id);
        orderItems.remove(orderItem);
        totalPrice.subtract(orderItem.getPrice());
    }

    public Long getId() {
        return id.getId();
    }

    public OrderItem getOrderItem(Long id) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException("Product with " + id + " doesn't exist."));
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    private void validateStatus() {
        if(OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state.");
        }
    }

    private void validateProduct(Product product) {
        if(product == null) {
            throw new DomainException("The product cannot be null.");
        }
    }
}
