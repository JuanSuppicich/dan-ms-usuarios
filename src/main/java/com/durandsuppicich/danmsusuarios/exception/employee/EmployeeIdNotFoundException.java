package com.durandsuppicich.danmsusuarios.exception.employee;

public class EmployeeIdNotFoundException extends EmployeeNotFoundException {

    private static final String DEFAULT_MESSAGE = "The given id does not belong to any employee. ";

    public EmployeeIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeIdNotFoundException(Integer id) {
        super(DEFAULT_MESSAGE + "{" + id + "}");
    }
}
