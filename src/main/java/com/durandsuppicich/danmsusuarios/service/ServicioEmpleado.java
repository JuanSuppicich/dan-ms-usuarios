package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.dao.EmpleadoJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Empleado;
import com.durandsuppicich.danmsusuarios.domain.TipoUsuario;
import com.durandsuppicich.danmsusuarios.domain.Usuario;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ServicioEmpleado implements IServicioEmpleado {

    private final EmpleadoJpaRepository empleadoRepository;

    public ServicioEmpleado(EmpleadoJpaRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado crear(Empleado empleado) {

        TipoUsuario tipoUsuario = new TipoUsuario(2, "Vendedor");
        Usuario usuario = new Usuario(empleado.getMail(), "1234", tipoUsuario);
        empleado.setUsuario(usuario);
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> todos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> empleadoPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Optional<Empleado> empleadoPorNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    @Override
    public void actualizar(Integer id, Empleado empleado) {

        if (empleadoRepository.existsById(id)) {

            empleadoRepository.save(empleado);

        } else {
            throw new NotFoundException("Empleado inexistente. Id: " + id);
        }
    }

    @Override
    public void eliminar(Integer id) {

        if (empleadoRepository.existsById(id)) {

            empleadoRepository.deleteById(id);

        } else {
            throw new NotFoundException("Empleado inexistente. Id: " + id);
        }
    }

}
