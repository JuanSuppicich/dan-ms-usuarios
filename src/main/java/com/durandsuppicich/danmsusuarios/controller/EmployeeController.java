package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.service.IEmployeeService;

import org.springframework.http.ResponseEntity;
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

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new employee")
    public ResponseEntity<Employee> post(@RequestBody Employee employee) {

        //if (employee.getUser() != null && employee.getUser().getPassword() != null) {

            Employee body = employeeService.post(employee);
            return ResponseEntity.ok(body);

        /*} else {
            throw new BadRequestException("Employee: " + employee);
        }*/
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all employees")
    public ResponseEntity<List<Employee>> getAll() {

        List<Employee> body = employeeService.getAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves an employee based on the given id")
    public ResponseEntity<Employee> getById(@PathVariable Integer id) {

        Optional<Employee> body = employeeService.getById(id);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Employee no encontrado. Id: " + id); //TODO Change this
        }
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Retrieves an employee based on the given name")
    public ResponseEntity<Employee> getByName(@RequestParam(name = "name", required = false) String name) {

        Optional<Employee> body = employeeService.getByName(name);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Employee no encontrado. Nombre: " + name); //TODO Change this
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates an employee based on the given id")
    public ResponseEntity<Employee> put(@RequestBody Employee employee, @PathVariable Integer id) {

        employeeService.put(employee, id); //TODO Change response
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes an employee based on the given id")
    public ResponseEntity<Employee> delete(@PathVariable Integer id) {

        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
