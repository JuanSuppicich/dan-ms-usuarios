package com.durandsuppicich.danmsusuarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {

private List<Cliente> clientes = new ArrayList<Cliente>();
private static Integer ID_GEN = 1; 

@GetMapping(path = "/{cuit}")
@ApiOperation(value = "Busca un cliente por id")
public ResponseEntity<Cliente> clientePorCuit(@PathVariable String cuit) {
    
    Optional<Cliente> cliente = clientes
        .stream()
        .filter(c -> c.getCuit().equals(cuit))
        .findFirst();
    return ResponseEntity.of(cliente);
}

@GetMapping()
@ApiOperation(value = "Busca un cliente por razon social")
public ResponseEntity<Cliente> clientePorRazonSocial(@RequestParam(name = "razonSocial") String razonSocial) {

    Optional<Cliente> cliente = clientes
        .stream()
        .filter(r -> r.getRazonSocial().equals(razonSocial))
        .findFirst();
    return ResponseEntity.of(cliente);
}
    
}
