package com.meli.hexagonal.infrastracture.repository.h2.order;

import com.meli.hexagonal.domain.OrderItem;
import com.meli.hexagonal.domain.Product;
import com.meli.hexagonal.domain.valueObject.CustomId;
import com.meli.hexagonal.domain.valueObject.Money;

import java.math.BigDecimal;

public class OrderItemEntity {

    private Long productId;
    private BigDecimal price;

    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderItem orderItem) {
        this.productId = orderItem.getProductId();
        this.price = orderItem.getPrice();
    }

    public OrderItem toOrderItem() {
        return new OrderItem(new Product(new CustomId(productId), new Money(price), ""));
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId() {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
