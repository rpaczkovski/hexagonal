package com.meli.hexagonal.domain;

import java.math.BigDecimal;

public class OrderItem {

    private Long productId;
    private BigDecimal price;

    private OrderItem() {}

    public OrderItem(Product product) {
        this.productId = product.getId();
        this.price = product.getPrice();
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addDiscount(BigDecimal discount) {
        validateDiscount(discount);
        price = price.multiply(price).divide(discount);
    }

    private void validateDiscount(BigDecimal discount) {
        if(discount == null) {
            throw new DomainException("The discount is valid.");
        }
        if(discount.compareTo(new BigDecimal(20)) >= 1) {
            throw new DomainException("The discount cannot be more than 20%.");
        }
    }

}
