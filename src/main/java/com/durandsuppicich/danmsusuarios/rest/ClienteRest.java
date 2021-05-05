package com.durandsuppicich.danmsusuarios.rest;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.exception.BadRequestException;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.service.IServicioCliente;

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
@RequestMapping("/api/cliente")
@Api(value = "ClienteRest", description = "Permite gestionar los clientes")
public class ClienteRest {

    private final IServicioCliente servicioCliente;

    public ClienteRest(IServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo cliente")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {

        if (cliente.getObras() != null && !cliente.getObras().isEmpty()) {

            if (cliente.getUsuario() != null && cliente.getUsuario().getClave() != null) {

                Cliente body = servicioCliente.crear(cliente);
                return ResponseEntity.ok(body);

            } else {
                throw new BadRequestException("Usuario: " + cliente.getUsuario());
            }
        } else {
            throw new BadRequestException("Obras: " + cliente.getObras());
        }
    }

    @GetMapping
    @ApiOperation(value = "Lista todos los clientes")
    public ResponseEntity<List<Cliente>> todos() {

        List<Cliente> body = servicioCliente.todos();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id) {

        Optional<Cliente> body = servicioCliente.clientePorId(id);

        if (body.isPresent()) {
            return ResponseEntity.of(body); // .ok(body) da error ?
        } else {
            throw new NotFoundException("Cliente no encontrado. Id: " + id);
        }
    }

    @GetMapping(params = "cuit")
    @ApiOperation(value = "Busca un cliente por cuit")
    public ResponseEntity<Cliente> clientePorCuit(@RequestParam(name = "cuit") String cuit) {

        Optional<Cliente> body = servicioCliente.clientePorCuit(cuit);

        if (body.isPresent()) {
            return ResponseEntity.of(body);
        } else {
            throw new NotFoundException("Cliente no encontrado. Cuit: " + cuit);
        }
    }

    @GetMapping(params = "razonSocial")
    @ApiOperation(value = "Busca un cliente por razon social")
    public ResponseEntity<Cliente> clientePorRazonSocial(
            @RequestParam(name = "razonSocial", required = false) String razonSocial) {

        Optional<Cliente> body = servicioCliente.clientePorRazonSocial(razonSocial);

        if (body.isPresent()) {
            return ResponseEntity.of(body);
        } else {
            throw new NotFoundException("Cliente no encontrado. Razon social: " + razonSocial);
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente en base al id")
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente, @PathVariable Integer id) {

        servicioCliente.actualizar(id, cliente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un cliente en base al id")
    public ResponseEntity<Cliente> eliminar(@PathVariable Integer id) {

        servicioCliente.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
