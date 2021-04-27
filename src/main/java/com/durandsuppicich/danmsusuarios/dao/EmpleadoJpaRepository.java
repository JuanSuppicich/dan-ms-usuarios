package com.durandsuppicich.danmsusuarios.dao;

import com.durandsuppicich.danmsusuarios.domain.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoJpaRepository extends JpaRepository<Empleado, Integer> { }
