package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPostDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPutDto;
import com.durandsuppicich.danmsusuarios.exception.BadRequestException;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.mapper.IConstructionMapper;
import com.durandsuppicich.danmsusuarios.service.IConstructionService;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    private final IConstructionMapper constructionMapper;

    public ConstructionController(IConstructionService constructionService,
                                  IConstructionMapper constructionMapper) {
        this.constructionService = constructionService;
        this.constructionMapper = constructionMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new construction site")
    public ResponseEntity<Construction> postConstruction(@RequestBody
                                                             @Validated ConstructionPostDto constructionDto) {

        if (constructionDto.getConstructionTypeId() != null) {

            Construction construction = constructionMapper.map(constructionDto);

            Construction body = constructionService.post(construction);
            return ResponseEntity.ok(body);

        } else {
            throw new BadRequestException("Tipo de construction: " + constructionDto.getConstructionTypeId()); //TODO Change this
        }
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all construction sites")
    public ResponseEntity<List<ConstructionDto>> getAll() {

        List<Construction> constructions = constructionService.getAll();
        List<ConstructionDto> body = constructionMapper.mapToDto(constructions);

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves a construction site based on the given id")
    public ResponseEntity<ConstructionDto> getById(@PathVariable
                                                       @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        Optional<Construction> construction = constructionService.getById(id);

        if (construction.isPresent()) {
            ConstructionDto body = constructionMapper.mapToDto(construction.get());
            return ResponseEntity.ok(body);
        } else {
            throw new NotFoundException("Construction no encontrada. Id: " + id); //TODO change this
        }
    }

    @GetMapping(params = { "customerId", "constructionTypeId" })
    @ApiOperation(value = "Retrieves construction sites by customer's id and/or construction site type. Both params" +
                            " are optional, if neither is given it works as getAll method")
    public ResponseEntity<List<ConstructionDto>> getByCustomerOrConstructionType(
            @RequestParam(required = false) @Range(min = 1, max = Integer.MAX_VALUE) Integer customerId,
            @RequestParam(required = false) @Range(min = 1, max = Integer.MAX_VALUE) Integer ConstructionTypeId) {

            List<Construction> constructions =
                    constructionService.getByCustomerOrConstructionType(customerId, ConstructionTypeId);

            List<ConstructionDto> body = constructionMapper.mapToDto(constructions);
            return ResponseEntity.ok(body);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a construction site based on the given id")
    public ResponseEntity<Construction> put(@RequestBody @Validated ConstructionPutDto constructionDto,
                                            @PathVariable @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        Construction construction = constructionMapper.map(constructionDto);
        constructionService.put(construction, id); //TODO Change response
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a construction site based on the given id")
    public ResponseEntity<Construction> delete(@PathVariable
                                                   @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        constructionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
