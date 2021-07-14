package com.durandsuppicich.danmsusuarios.exception.customer;

import com.durandsuppicich.danmsusuarios.exception.customer.CustomerNotFoundException;

public class BusinessNameNotFoundException extends CustomerNotFoundException {

    private static String DEFAULT_MESSAGE = "The given business name does not belong to any customer. ";

    public BusinessNameNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public BusinessNameNotFoundException(String name) {
        super(DEFAULT_MESSAGE + "{" + name + "}");
    }
}
