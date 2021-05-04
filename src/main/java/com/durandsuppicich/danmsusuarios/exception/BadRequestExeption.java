package com.durandsuppicich.danmsusuarios.exception;

//Este tipo de clase generica se pondria extender para especificar. 
public class BadRequestExeption extends RuntimeException {
    
    private static final String DESCRIPCION = "Bad Request Exception (400)";

    public BadRequestExeption(String detalle) {
        super(DESCRIPCION + ". " + detalle);
    }
}
