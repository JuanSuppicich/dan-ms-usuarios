package com.durandsuppicich.danmsusuarios.exception.construction;

public class ConstructionIdNotFoundException extends ConstructionNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given id does not belong to any construction. ";

    public ConstructionIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ConstructionIdNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + "{" + id + "}");
    }
}
