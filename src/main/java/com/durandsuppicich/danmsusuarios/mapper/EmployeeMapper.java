package com.durandsuppicich.danmsusuarios.mapper;

import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeeDetailsDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeeDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePostDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePutDto;

public class EmployeeMapper implements IEmployeeMapper {

    @Override
    public Employee map(EmployeePostDto employeeDto) {

        Employee employee = new Employee();

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        return employee;
    }

    @Override
    public Employee map(EmployeePutDto employeeDto) {

        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        return employee;
    }

    @Override
    public EmployeeDto mapToDto(Employee employee) {

        EmployeeDto dto = new EmployeeDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());

        return dto;
    }

    @Override
    public EmployeeDetailsDto mapToDetailsDto(Employee employee) {
        return null;
    }
}
