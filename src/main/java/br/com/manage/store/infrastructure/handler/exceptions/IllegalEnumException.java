package br.com.manage.store.infrastructure.handler.exceptions;

public class IllegalEnumException extends RuntimeException{
    public IllegalEnumException() {
    }

    public IllegalEnumException(String message) {
        super(message);
    }
}
