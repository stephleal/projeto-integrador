package com.w4.projetoIntegrador.exceptions;

public class CartCannotBeCancelException extends RuntimeException{

    public CartCannotBeCancelException() {

        super("Carrinho com status fechado não pode ser cancelado");
    }
}
