package com.durandsuppicich.danmsusuarios.repository;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByName(String name);

}
