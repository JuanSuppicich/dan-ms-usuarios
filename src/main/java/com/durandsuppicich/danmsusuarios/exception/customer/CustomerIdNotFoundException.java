package com.durandsuppicich.danmsusuarios.exception.customer;

public class CustomerIdNotFoundException extends CustomerNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given id does not belong to any customer. ";

    public CustomerIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerIdNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + "{" + id + "}");
    }

}
