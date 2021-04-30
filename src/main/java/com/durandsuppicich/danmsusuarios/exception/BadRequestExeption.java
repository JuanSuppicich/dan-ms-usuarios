package com.durandsuppicich.danmsusuarios.exception;

public class BadRequestExeption extends RuntimeException {
    
    private static final String DESCRIPCION = "Bad Request Exception (400)";

    public BadRequestExeption(String detalle) {
        super(DESCRIPCION + ". " + detalle);
    }
}
