package com.durandsuppicich.danmsusuarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

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
@Api(value = "ClienteRest", description =  "Permite gestionar los clientes")
public class ClienteRest {

    private List<Cliente> clientes = new ArrayList<Cliente>();
    private static Integer ID_GEN = 1;


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id){

        Optional<Cliente> cliente =  clientes
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(cliente);
    }

    @GetMapping
    @ApiOperation(value = "Lista todos los clientes")
    public ResponseEntity<List<Cliente>> todos(){
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo cliente")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        cliente.setId(ID_GEN++);
        clientes.add(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente en base al id")
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente,  @PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, clientes.size())
        .filter(i -> clientes.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            clientes.set(indexOpt.getAsInt(), cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un cliente en base al id")
    public ResponseEntity<Cliente> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, clientes.size())
        .filter(i -> clientes.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            clientes.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{cuit}")
    @ApiOperation(value = "Busca un cliente por cuit")
    public ResponseEntity<Cliente> clientePorCuit(@PathVariable String cuit) {
        Optional<Cliente> cliente = clientes
            .stream()
            .filter(c -> c.getCuit().equals(cuit))
            .findFirst();
        return ResponseEntity.of(cliente);
    }

    @GetMapping(params = "razonSocial")
    @ApiOperation(value = "Busca un cliente por razon social")
    public ResponseEntity<Cliente> clientePorRazonSocial(@RequestParam(name = "razonSocial", required = false) String razonSocial) {
        Optional<Cliente> cliente = clientes
            .stream()
            .filter(r -> r.getRazonSocial().equals(razonSocial))
            .findFirst();
        return ResponseEntity.of(cliente);
    }
    
}
