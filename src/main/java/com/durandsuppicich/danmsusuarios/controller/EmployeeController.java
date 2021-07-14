package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeeDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePostDto;
import com.durandsuppicich.danmsusuarios.dto.employee.EmployeePutDto;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.mapper.IEmployeeMapper;
import com.durandsuppicich.danmsusuarios.service.IEmployeeService;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
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

@RestController
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
    public ResponseEntity<Employee> post(@RequestBody @Validated EmployeePostDto employeeDto) {

        //if (employee.getUser() != null && employee.getUser().getPassword() != null) {
            Employee employee = employeeMapper.map(employeeDto);
            Employee body = employeeService.post(employee);

            return ResponseEntity.ok(body);

        /*} else {
            throw new BadRequestException("Employee: " + employee);
        }*/
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
                                                   @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        Optional<Employee> employee = employeeService.getById(id);

        if (employee.isPresent()) {
            EmployeeDto body = employeeMapper.mapToDto(employee.get());
            return ResponseEntity.ok(body);
        } else {
            throw new NotFoundException("Employee no encontrado. Id: " + id); //TODO Change this
        }
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Retrieves an employee based on the given name")
    public ResponseEntity<EmployeeDto> getByName(@RequestParam(name = "name", required = false)
                                                     @Length(max = 32) String name) {

        Optional<Employee> employee = employeeService.getByName(name);

        if (employee.isPresent()) {
            EmployeeDto body = employeeMapper.mapToDto(employee.get());
            return ResponseEntity.ok(body);
        } else {
            throw new NotFoundException("Employee no encontrado. Nombre: " + name); //TODO Change this
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates an employee based on the given id")
    public ResponseEntity<?> put(@RequestBody @Validated EmployeePutDto employeeDto,
                                 @PathVariable @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        Employee employee = employeeMapper.map(employeeDto);

        employeeService.put(employee, id); //TODO Change response
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes an employee based on the given id")
    public ResponseEntity<?> delete(@PathVariable @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
