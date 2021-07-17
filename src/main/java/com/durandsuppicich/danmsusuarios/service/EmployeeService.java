package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.util.List;

import com.durandsuppicich.danmsusuarios.exception.employee.EmployeeIdNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.employee.EmployeeNameNotFoundException;
import com.durandsuppicich.danmsusuarios.repository.IEmployeeJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Employee;
import com.durandsuppicich.danmsusuarios.domain.User;
import com.durandsuppicich.danmsusuarios.domain.UserType;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeJpaRepository employeeRepository;

    public EmployeeService(IEmployeeJpaRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee post(Employee employee) {

        UserType userType = new UserType(2, "Vendedor");
        User user = new User(employee.getEmail(), "1234", userType);
        employee.setUser(user);
        employee.setPostDate(Instant.now());

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeIdNotFoundException(id));
    }

    @Override
    public Employee getByName(String name) {
        return employeeRepository.findByName(name)
                .orElseThrow(() -> new EmployeeNameNotFoundException(name));
    }

    @Override
    public void put(Employee employee, Integer id) {

       employeeRepository.findById(id)
               .map(e -> {
                   e.setPutDate(Instant.now());
                   e.setName(employee.getName());
                   e.setEmail(employee.getEmail());
                   return employeeRepository.save(e);
               })
               .orElseThrow(() -> new EmployeeIdNotFoundException(id));
    }

    @Override
    public void delete(Integer id) {

        if (employeeRepository.existsById(id)) {

            employeeRepository.deleteById(id);

        } else {
            throw new EmployeeIdNotFoundException(id);
        }
    }
}
