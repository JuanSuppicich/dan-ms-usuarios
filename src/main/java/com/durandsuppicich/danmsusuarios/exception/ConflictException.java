package com.durandsuppicich.danmsusuarios.exception;

public class ConflictException extends RuntimeException {

    private static final String DESCRIPCION = "Conflict Exception (409)";

    public ConflictException(String detalle) {
        super(DESCRIPCION + ". " + detalle);
    }

}
