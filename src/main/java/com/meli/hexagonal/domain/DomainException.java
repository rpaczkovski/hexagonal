package com.meli.hexagonal.domain;

class DomainException extends RuntimeException {
    DomainException(final String message) {
        super(message);
    }
}
