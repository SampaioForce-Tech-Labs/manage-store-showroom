package br.com.manage.store.infrastructure.handler.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
    }
}
