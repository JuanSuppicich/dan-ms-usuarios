package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Employee;

public interface IEmployeeService {

    Employee post(Employee employee);

    List<Employee> getAll();

    Optional<Employee> getById(Integer id);

    Optional<Employee> getByName(String name);

    void put(Employee employee, Integer id);

    void delete(Integer id);

}
