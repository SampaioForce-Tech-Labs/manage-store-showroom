package br.com.manage.store.infrastructure.handler.exceptions;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(String message){
        super(message);
    }

    public InsufficientStockException(){}
}
