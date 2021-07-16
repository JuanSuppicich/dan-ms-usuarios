package com.durandsuppicich.danmsusuarios.exception.employee;

import com.durandsuppicich.danmsusuarios.exception.http.NotFoundException;

public class EmployeeNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Employee could not be found. ";

    public EmployeeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeNotFoundException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
}
