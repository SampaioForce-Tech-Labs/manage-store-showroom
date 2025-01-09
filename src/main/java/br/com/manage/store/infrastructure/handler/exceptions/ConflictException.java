package br.com.manage.store.infrastructure.handler.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }
}
