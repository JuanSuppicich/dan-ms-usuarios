package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Empleado;

public interface IServicioEmpleado {

    Empleado crear(Empleado empleado);
    List<Empleado> todos();
    Optional<Empleado> empleadoPorId(Integer id);
    Optional<Empleado> empleadoPorNombre(String nombre);
    void actualizar(Integer id, Empleado empleado);
    void eliminar(Integer id);
    
}
