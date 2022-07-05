package com.meli.hexagonal.application.response;

public class CreateOrderResponse {
    private final Long id;

    public CreateOrderResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
