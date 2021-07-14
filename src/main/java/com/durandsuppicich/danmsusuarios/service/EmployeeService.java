package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.repository.IEmployeeJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.domain.User;
import com.durandsuppicich.danmsusuarios.domain.UserType;
import com.durandsuppicich.danmsusuarios.exception.http.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeJpaRepository employeeRepository;

    public EmployeeService(IEmployeeJpaRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee post(Employee employee) {

        UserType userType = new UserType(2, "Vendedor"); //TODO improve this
        User user = new User(employee.getEmail(), "1234", userType);
        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> getByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public void put(Employee employee, Integer id) {

        if (employeeRepository.existsById(id)) {

            employeeRepository.save(employee);

        } else {
            throw new NotFoundException("Employee inexistente. Id: " + id); //TODO change this
        }
    }

    @Override
    public void delete(Integer id) {

        if (employeeRepository.existsById(id)) {

            employeeRepository.deleteById(id);

        } else {
            throw new NotFoundException("Employee inexistente. Id: " + id); //TODO change this
        }
    }

}
