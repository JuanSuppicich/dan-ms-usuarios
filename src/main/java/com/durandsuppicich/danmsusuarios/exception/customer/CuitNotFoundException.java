package com.durandsuppicich.danmsusuarios.exception.customer;

import com.durandsuppicich.danmsusuarios.exception.customer.CustomerNotFoundException;

public class CuitNotFoundException extends CustomerNotFoundException {

    private static String DEFAULT_MESSAGE = "The given cuit does not belong to any customer. ";

    public CuitNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CuitNotFoundException(String cuit) {
        super(DEFAULT_MESSAGE + "{" + cuit + "}");
    }
}
