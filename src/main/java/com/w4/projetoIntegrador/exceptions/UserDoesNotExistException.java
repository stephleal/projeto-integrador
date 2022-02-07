package com.w4.projetoIntegrador.exceptions;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException() {

        super("Usuário não cadastrado no sistema");
    }
}
