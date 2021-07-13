package com.durandsuppicich.danmsusuarios.mapper;

import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeeDetailsDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeeDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePostDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePutDto;

public interface IEmployeeMapper {

    Employee map(EmployeePostDto employeeDto);

    Employee map(EmployeePutDto employeeDto);

    EmployeeDto mapToDto(Employee employee);

    EmployeeDetailsDto mapToDetailsDto(Employee employee);
}
