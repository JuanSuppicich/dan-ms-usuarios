package com.durandsuppicich.danmsusuarios.dao;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoJpaRepository extends JpaRepository<Empleado, Integer> { 

    Optional<Empleado> findByNombre(String nombre); //Puede ser una lista? 

}
