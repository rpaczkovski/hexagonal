package com.meli.hexagonal.domain.valueObject;

import java.math.BigDecimal;

enum Currency {
    BRL, USD, ARG
}

public class Money {

    private BigDecimal price;
    private Currency currency;

    public Money(BigDecimal price) {
        validatePrice(price);
        this.price = price;
        this.currency = Currency.BRL;
    }

    private void validatePrice(BigDecimal price) {
        if(price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("The price cannot be less than zero.");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }
}
