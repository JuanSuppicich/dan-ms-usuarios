package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeeDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePostDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePutDto;
import com.durandsuppicich.danmsusuarios.exception.validation.IdsNotMatchException;
import com.durandsuppicich.danmsusuarios.mapper.IEmployeeMapper;
import com.durandsuppicich.danmsusuarios.service.IEmployeeService;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/api/employee")
@Api(value = "EmployeeController")
public class EmployeeController {

    private final IEmployeeService employeeService;

    private final IEmployeeMapper employeeMapper;

    public EmployeeController(IEmployeeService employeeService,
                              IEmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new employee")
    public ResponseEntity<Employee> post(@RequestBody @Valid EmployeePostDto employeeDto) {

        Employee employee = employeeMapper.map(employeeDto);
        Employee body = employeeService.post(employee);

        return ResponseEntity.ok(body);
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all employees")
    public ResponseEntity<List<EmployeeDto>> getAll() {

        List<Employee> employees = employeeService.getAll();
        List<EmployeeDto> body = employeeMapper.mapToDto(employees);

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves an employee based on the given id")
    public ResponseEntity<EmployeeDto> getById(@PathVariable
                                                   @Positive Integer id) {

        Employee employee = employeeService.getById(id);
        EmployeeDto body = employeeMapper.mapToDto(employee);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Retrieves an employee based on the given name")
    public ResponseEntity<EmployeeDto> getByName(@RequestParam(name = "name", required = false)
                                                     @Length(max = 32) String name) {

        Employee employee = employeeService.getByName(name);
        EmployeeDto body = employeeMapper.mapToDto(employee);

        return ResponseEntity.ok(body);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates an employee based on the given id")
    public ResponseEntity<?> put(@RequestBody @Valid EmployeePutDto employeeDto,
                                 @PathVariable @Positive Integer id) {

        if (!employeeDto.getId().equals(id))
            throw new IdsNotMatchException(employeeDto.getId(), id);

        Employee employee = employeeMapper.map(employeeDto);
        employeeService.put(employee, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes an employee based on the given id")
    public ResponseEntity<?> delete(@PathVariable @Positive Integer id) {

        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
