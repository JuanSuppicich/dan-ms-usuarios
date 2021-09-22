package com.durandsuppicich.danmsusuarios.controller;

import java.net.URI;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.dto.OnConstructionPost;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPostDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPutDto;
import com.durandsuppicich.danmsusuarios.exception.validation.IdsNotMatchException;
import com.durandsuppicich.danmsusuarios.mapper.IConstructionMapper;
import com.durandsuppicich.danmsusuarios.service.IConstructionService;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Mod11Check;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/api/constructions")
@Api(value = "ConstructionController")
@CrossOrigin(origins = "*", methods=
        {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ConstructionController {

    private final IConstructionService constructionService;

    private final IConstructionMapper constructionMapper;

    public ConstructionController(IConstructionService constructionService,
                                  IConstructionMapper constructionMapper) {
        this.constructionService = constructionService;
        this.constructionMapper = constructionMapper;
    }

    @PostMapping
    @Validated(OnConstructionPost.class)
    @ApiOperation(value = "Creates a new construction site")
    public ResponseEntity<ConstructionDto> post(@RequestBody
                                                 @Valid ConstructionPostDto constructionDto) {

        Construction construction = constructionMapper.map(constructionDto);
        Construction result = constructionService.post(construction);
        ConstructionDto body = constructionMapper.mapToDto(result);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();

        return ResponseEntity.created(location).body(body);
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
                                                       @Positive Integer id) {

        Construction construction = constructionService.getById(id);
        ConstructionDto body = constructionMapper.mapToDto(construction);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = { "customerId", "constructionTypeId" })
    @ApiOperation(value = "Retrieves construction sites by customer's id and/or construction site type. Both params" +
                            " are optional, if neither is given it works as getAll method")
    public ResponseEntity<List<ConstructionDto>> getByCustomerOrConstructionType(
            @RequestParam(required = false) @Positive Integer customerId,
            @RequestParam(required = false) @Positive Integer ConstructionTypeId) {

            List<Construction> constructions =
                    constructionService.getByCustomerOrConstructionType(customerId, ConstructionTypeId);
            List<ConstructionDto> body = constructionMapper.mapToDto(constructions);

            return ResponseEntity.ok(body);
    }

    @GetMapping(params = { "cuit" })
    @ApiOperation(value = "Retrieves construction sites by customer's cuit")
    public ResponseEntity<List<ConstructionDto>> getByCuit(
            @RequestParam(required = false)
            @NotBlank
            @Length(min = 11, max = 11)
            @Mod11Check(threshold = 7) String cuit) {

        List<Construction> constructions = constructionService.getByCuit(cuit);
        List<ConstructionDto> body = constructionMapper.mapToDto(constructions);

        return ResponseEntity.ok(body);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a construction site based on the given id")
    public ResponseEntity<?> put(@RequestBody @Validated ConstructionPutDto constructionDto,
                                            @PathVariable @Positive Integer id) {

        if (!constructionDto.getId().equals(id))
            throw new IdsNotMatchException(constructionDto.getId(), id);

        Construction construction = constructionMapper.map(constructionDto);
        constructionService.put(construction, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a construction site based on the given id")
    public ResponseEntity<?> delete(@PathVariable @Positive Integer id) {

        constructionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
