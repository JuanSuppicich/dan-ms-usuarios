package com.durandsuppicich.danmsusuarios.service;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Employee;

public interface IEmployeeService {

    Employee post(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee getByName(String name);

    void put(Employee employee, Integer id);

    void delete(Integer id);

}
