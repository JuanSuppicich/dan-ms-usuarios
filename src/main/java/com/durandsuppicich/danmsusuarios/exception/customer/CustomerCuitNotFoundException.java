package com.durandsuppicich.danmsusuarios.exception.customer;

public class CustomerCuitNotFoundException extends CustomerNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given cuit does not belong to any customer. ";

    public CustomerCuitNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerCuitNotFoundException(String cuit) {
        super(DEFAULT_MESSAGE + "{" + cuit + "}");
    }
}
