package com.durandsuppicich.danmsusuarios.exception.customer;

import com.durandsuppicich.danmsusuarios.exception.http.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Customer could not be found. ";

    public CustomerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerNotFoundException(String message) {
        super(DEFAULT_MESSAGE + message);
    }

}
