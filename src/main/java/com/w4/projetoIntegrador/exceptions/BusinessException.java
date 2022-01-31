package com.w4.projetoIntegrador.exceptions;


public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}