package com.durandsuppicich.danmsusuarios.exception.customer;

public class ConstructionIdNotFoundException extends CustomerNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given construction id does not match with any customer. ";

    public ConstructionIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ConstructionIdNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + "{" + id + "}");
    }
}
