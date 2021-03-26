package com.durandsuppicich.danmsusuarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.TipoObra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/obra")
@Api(value = "ObraRest", description =  "Permite gestionar las obras")
public class ObraRest {

    private Integer ID_GEN = 0;
    private List<Obra> obras = new ArrayList<Obra>();


    @PostMapping
    @ApiOperation(value = "Crea una nueva obra")
    public ResponseEntity<Obra> crear(@RequestBody Obra obra) {
        obra.setId(ID_GEN++);
        obras.add(obra);
        return ResponseEntity.ok(obra);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza una obra en base al id")
    public ResponseEntity<Obra> actualizar(@RequestBody Obra obra, @PathVariable Integer id) {

        OptionalInt index = IntStream.range(0, obras.size())
            .filter(i -> obras.get(i).getId().equals(id))
            .findFirst();

        if (index.isPresent()) {
            obras.set(index.getAsInt(), obra);
            return ResponseEntity.ok(obra);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "Elimina una obra en base al id")
    public ResponseEntity<Obra> borrar(@PathVariable Integer id) {

        OptionalInt index = IntStream.range(0, obras.size())
            .filter(i -> obras.get(i).getId().equals(id))
            .findFirst();

        if (index.isPresent()) {
            obras.remove(index.getAsInt());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca una obra por id")
    public ResponseEntity<Obra> obraPorId(@PathVariable Integer id) {
        Optional<Obra> obra = obras
            .stream()
            .filter(o -> o.getId().equals(id))
            .findFirst();
        return ResponseEntity.of(obra);
    }

    @GetMapping
    @ApiOperation(value = "Busca una por cliente y/o tipo de obra")
    public ResponseEntity<Obra> obraPorClienteOTipoDeObra(@RequestBody(required = false) Cliente cliente, 
        @RequestBody(required = false) TipoObra tipoObra) {
        
        if (cliente != null && tipoObra != null) {
            Optional<Obra> obra = obras
                .stream()
                .filter(o -> o.getCliente().equals(cliente) && o.getTipoObra().equals(tipoObra))
                .findFirst();
            return ResponseEntity.of(obra);
        }
        else {
            if (cliente != null) {
                Optional<Obra> obra = obras
                    .stream()
                    .filter(o -> o.getCliente().equals(cliente))
                    .findFirst();
                return ResponseEntity.of(obra);
            }
            else {
                if (tipoObra != null) {
                    Optional<Obra> obra = obras
                        .stream()
                        .filter(o -> o.getTipoObra().equals(tipoObra))
                        .findFirst();
                    return ResponseEntity.of(obra);
                }
                else {
                    return ResponseEntity.notFound().build();
                }
            }
        }
    }
}
