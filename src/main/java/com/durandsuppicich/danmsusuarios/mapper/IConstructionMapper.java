package com.durandsuppicich.danmsusuarios.mapper;

import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionDetailsDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPostDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPutDto;

import java.util.List;

public interface IConstructionMapper {

    Construction map(ConstructionPostDto constructionDto);

    Construction map(ConstructionPutDto constructionDto);

    ConstructionDto mapToDto(Construction construction);

    ConstructionDetailsDto mapToDetailsDto(Construction construction);

    List<ConstructionDto> mapToDto(List<Construction> constructions);
}
