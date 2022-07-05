package com.meli.hexagonal.domain;

import com.meli.hexagonal.domain.valueObject.CustomId;
import com.meli.hexagonal.domain.valueObject.Money;

import java.math.BigDecimal;

public class Product {
    private CustomId id;
    private Money price;
    private String name;

    public Product(CustomId id, Money price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Product(Money price, String name) {
        this.id = CustomId.randomId();
        this.price = price;
        this.name = name;
    }

    public Long getId() {
        return id.getId();
    }

    public BigDecimal getPrice() {
        return price.getPrice();
    }

    public String getName() {
        return name;
    }
}
