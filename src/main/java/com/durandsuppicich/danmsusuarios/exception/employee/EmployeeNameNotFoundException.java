package com.durandsuppicich.danmsusuarios.exception.employee;

public class EmployeeNameNotFoundException extends EmployeeNotFoundException {

    private static String DEFAULT_MESSAGE = "The given name does not belong to any employee. ";

    public EmployeeNameNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeNameNotFoundException(String name) {
        super(DEFAULT_MESSAGE + "{" + name + "}");
    }
}
