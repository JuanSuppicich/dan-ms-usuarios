package com.durandsuppicich.danmsusuarios.exception.construction;

import com.durandsuppicich.danmsusuarios.exception.http.NotFoundException;

public class ConstructionNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Construction could not be found. ";

    public ConstructionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ConstructionNotFoundException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
}
