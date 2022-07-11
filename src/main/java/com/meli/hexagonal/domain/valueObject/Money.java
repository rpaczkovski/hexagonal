package com.meli.hexagonal.domain.valueObject;

import java.math.BigDecimal;

enum Currency {
    BRL, USD, ARG
}

public class Money {

    private BigDecimal value;
    private Currency currency;

    public Money(BigDecimal price) {
        this.value = price;
        this.currency = Currency.BRL;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
