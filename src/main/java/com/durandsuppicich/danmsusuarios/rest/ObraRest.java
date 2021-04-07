package com.durandsuppicich.danmsusuarios.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsusuarios.domain.Obra;

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
@RequestMapping("/api/obra")
@Api(value = "ObraRest", description =  "Permite gestionar las obras")
public class ObraRest {

    private Integer ID_GEN = 1;
    private List<Obra> obras = new ArrayList<Obra>();


    @PostMapping
    @ApiOperation(value = "Crea una nueva obra")
    public ResponseEntity<Obra> crear(@RequestBody Obra obra) {
        obra.setId(ID_GEN++);
        obras.add(obra);
        return ResponseEntity.ok(obra);
    }

    @GetMapping
    @ApiOperation(value = "Lista todos las obras")
    public ResponseEntity<List<Obra>> todos() {
        return ResponseEntity.ok(obras);
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

    @GetMapping(params = {"idCliente","idTipoObra"})
    @ApiOperation(value = "Lista obras por id cliente y/o id tipo de obra")
    public ResponseEntity<List<Obra>> obrasPorClienteOTipoDeObra(
        @RequestParam(required = false) Integer idCliente,
        @RequestParam(required = false) Integer idTipoObra) {
        
        List<Obra> obras = List.copyOf(this.obras);

        if (idCliente != null) {
            obras = obras
                .stream()
                .filter(o -> o.getCliente().getId().equals(idCliente))
                .collect(Collectors.toList());
        }
        if (idTipoObra != null) {
            obras = obras
                .stream()
                .filter(o -> o.getTipoObra().getId().equals(idTipoObra))
                .collect(Collectors.toList());
        }
        if (idCliente == null && idTipoObra == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(obras);
        }
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

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina una obra en base al id")
    public ResponseEntity<Obra> eliminar(@PathVariable Integer id) {

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
}
