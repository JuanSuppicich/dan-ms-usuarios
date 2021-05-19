package com.durandsuppicich.danmsusuarios.rest;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Empleado;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.service.IServicioEmpleado;

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
@RequestMapping("/api/empleado")
@Api(value = "EmpleadoRest", description = "Permite gestionar los empleados")
public class EmpleadoRest {

    private final IServicioEmpleado servicioEmpleado;

    public EmpleadoRest(IServicioEmpleado servicioEmpleado) {
        this.servicioEmpleado = servicioEmpleado;
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo empleado")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {

        //if (empleado.getUsuario() != null && empleado.getUsuario().getClave() != null) {

            Empleado body = servicioEmpleado.crear(empleado);
            return ResponseEntity.ok(body);

        /*} else {
            throw new BadRequestException("Empleado: " + empleado);
        }*/
    }

    @GetMapping
    @ApiOperation(value = "Lista todos los empleados")
    public ResponseEntity<List<Empleado>> todos() {

        List<Empleado> body = servicioEmpleado.todos();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un empleado por id")
    public ResponseEntity<Empleado> empleadoPorId(@PathVariable Integer id) {

        Optional<Empleado> body = servicioEmpleado.empleadoPorId(id);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Empleado no encontrado. Id: " + id);
        }
    }

    @GetMapping(params = "nombre")
    @ApiOperation(value = "Busca un empleado por nombre")
    public ResponseEntity<Empleado> empleadoPorNombre(@RequestParam(name = "nombre", required = false) String nombre) {

        Optional<Empleado> body = servicioEmpleado.empleadoPorNombre(nombre);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Empleado no encontrado. Nombre: " + nombre);
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un empleado en base al id")
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado empleado, @PathVariable Integer id) {

        servicioEmpleado.actualizar(id, empleado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un empleado en base al id")
    public ResponseEntity<Empleado> eliminar(@PathVariable Integer id) {

        servicioEmpleado.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
