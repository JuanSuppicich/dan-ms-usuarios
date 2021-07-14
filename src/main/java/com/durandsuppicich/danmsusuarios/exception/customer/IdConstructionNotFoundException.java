package com.durandsuppicich.danmsusuarios.exception.customer;

public class IdConstructionNotFoundException extends CustomerNotFoundException {

    private static String DEFAULT_MESSAGE = "The given construction id does not match with any customer. ";

    public IdConstructionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public IdConstructionNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + "{" + id + "}");
    }
}
