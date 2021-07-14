package com.durandsuppicich.danmsusuarios.exception.customer;

public class IdNotFoundException extends CustomerNotFoundException {

    private static String DEFAULT_MESSAGE = "The given id does not belong to any customer. ";

    public IdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public IdNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + "{" + id + "}");
    }

}
