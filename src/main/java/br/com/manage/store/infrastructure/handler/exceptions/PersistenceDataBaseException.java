package br.com.manage.store.infrastructure.handler.exceptions;

public class PersistenceDataBaseException extends RuntimeException{

    public PersistenceDataBaseException(String message){
        super(message);
    }

    public PersistenceDataBaseException(){}
}
