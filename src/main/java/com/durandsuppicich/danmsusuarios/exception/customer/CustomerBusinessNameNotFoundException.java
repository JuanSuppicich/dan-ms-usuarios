package com.durandsuppicich.danmsusuarios.exception.customer;

public class CustomerBusinessNameNotFoundException extends CustomerNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given business name does not belong to any customer. ";

    public CustomerBusinessNameNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerBusinessNameNotFoundException(String name) {
        super(DEFAULT_MESSAGE + "{" + name + "}");
    }
}
