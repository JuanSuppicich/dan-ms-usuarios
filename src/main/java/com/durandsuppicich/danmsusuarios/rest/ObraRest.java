package com.durandsuppicich.danmsusuarios.rest;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.exception.BadRequestExeption;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.service.IServicioObra;

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
@Api(value = "ObraRest", description = "Permite gestionar las obras")
public class ObraRest {

    private final IServicioObra servicioObra;

    public ObraRest(IServicioObra servicioObra) {
        this.servicioObra = servicioObra;
    }

    @PostMapping
    @ApiOperation(value = "Crea una nueva obra")
    public ResponseEntity<Obra> crear(@RequestBody Obra obra) {

        Obra body = servicioObra.crear(obra);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    @ApiOperation(value = "Lista todos las obras")
    public ResponseEntity<List<Obra>> todos() {

        List<Obra> body = servicioObra.todos();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca una obra por id")
    public ResponseEntity<Obra> obraPorId(@PathVariable Integer id) {

        Optional<Obra> body = servicioObra.obraPorId(id);

        if (body.isPresent()) {
            return ResponseEntity.of(body); // .ok(body) da error ?
        } else {
            throw new NotFoundException("Obra no encontrada. Id: " + id);
        }
    }

    @GetMapping(params = { "idCliente", "idTipoObra" })
    @ApiOperation(value = "Lista obras por id cliente y/o id tipo de obra")
    public ResponseEntity<List<Obra>> obrasPorClienteOTipoDeObra(@RequestParam(required = false) Integer idCliente,
            @RequestParam(required = false) Integer idTipoObra) {

            List<Obra> body = servicioObra.obrasPorClienteOTipoObra(idCliente, idTipoObra);
            return ResponseEntity.ok(body);
            
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza una obra en base al id")
    public ResponseEntity<Obra> actualizar(@RequestBody Obra obra, @PathVariable Integer id) {

        servicioObra.actualizar(obra, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina una obra en base al id")
    public ResponseEntity<Obra> eliminar(@PathVariable Integer id) {

        servicioObra.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
