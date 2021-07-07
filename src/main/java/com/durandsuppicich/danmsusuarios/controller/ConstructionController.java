package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.exception.BadRequestException;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.service.IConstructionService;

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
@RequestMapping("/api/construction")
@Api(value = "ConstructionController")
public class ConstructionController {

    private final IConstructionService constructionService;

    public ConstructionController(IConstructionService constructionService) {
        this.constructionService = constructionService;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new construction site")
    public ResponseEntity<Construction> postConstruction(@RequestBody Construction construction) {

        if (construction.getConstructionType() != null && construction.getConstructionType().getId() != null) {

            Construction body = constructionService.post(construction);
            return ResponseEntity.ok(body);

        } else {
            throw new BadRequestException("Tipo de construction: " + construction.getConstructionType()); //TODO Change this
        }
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all construction sites")
    public ResponseEntity<List<Construction>> getAll() {

        List<Construction> body = constructionService.getAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves a construction site based on the given id")
    public ResponseEntity<Construction> getById(@PathVariable Integer id) {

        Optional<Construction> body = constructionService.getById(id);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Construction no encontrada. Id: " + id); //TODO change this
        }
    }

    @GetMapping(params = { "customerId", "constructionTypeId" })
    @ApiOperation(value = "Retrieves construction sites by customer's id and/or construction site type. Both params" +
                            " are optional, if neither is given it works as getAll method")
    public ResponseEntity<List<Construction>> getByCustomerOrConstructionType(
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Integer ConstructionTypeId) {

            List<Construction> body =
                    constructionService.getByCustomerOrConstructionType(customerId, ConstructionTypeId);
            return ResponseEntity.ok(body);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a construction site based on the given id")
    public ResponseEntity<Construction> put(@RequestBody Construction construction, @PathVariable Integer id) {

        constructionService.put(construction, id); //TODO Change response
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a construction site based on the given id")
    public ResponseEntity<Construction> delete(@PathVariable Integer id) {

        constructionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
