package com.durandsuppicich.danmsusuarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsusuarios.domain.Empleado;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/empleado")
@Api(value = "ClienteRest", description =  "Permite gestionar los empleados")
public class EmpleadoRest {
    
    private Integer ID_GEN = 0;
    private List<Empleado> empleados = new ArrayList<Empleado>();


    @PostMapping
    @ApiOperation(value = "Crea un nuevo empleado")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
        empleado.setId(ID_GEN++);
        empleados.add(empleado);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un empleado en base al id")
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado empleado, @PathVariable Integer id) {

        OptionalInt index = IntStream.range(0, empleados.size())
            .filter(i -> empleados.get(i).getId().equals(id))
            .findFirst();

        if (index.isPresent()) {
            empleados.set(index.getAsInt(), empleado);
            return ResponseEntity.ok(empleado);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un empleado en base al id")
    public ResponseEntity<Empleado> eliminar(@PathVariable Integer id) {

        OptionalInt index = IntStream.range(0, empleados.size())
            .filter(i -> empleados.get(i).getId().equals(id))
            .findFirst();

        if (index.isPresent()) {
            empleados.remove(index.getAsInt());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un empleado por id")
    public ResponseEntity<Empleado> empleadoPorId(@PathVariable Integer id) {
        Optional<Empleado> empleado = empleados
            .stream()
            .filter(e -> e.getId().equals(id))
            .findFirst();
        return ResponseEntity.of(empleado);
    }

    @GetMapping(params = "name")
    @ApiOperation(value = "Busca un empleado por nombre")
    public ResponseEntity<Empleado> empleadoPorNombre(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            Optional<Empleado> empleado = empleados
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
            return ResponseEntity.of(empleado);
        }
        else {
            return ResponseEntity.notFound().build();
        }
        
    }

}
