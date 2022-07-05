package com.meli.hexagonal.domain.valueObject;

import java.util.UUID;

public class CustomId {
    private Long id;

    public static CustomId randomId() {
        return new CustomId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }

    public CustomId(Long id) {
        this.id = id;
    }

    public CustomId() {
        this.id = randomId().getId();
    }

    public Long getId() {
        return id;
    }
}
