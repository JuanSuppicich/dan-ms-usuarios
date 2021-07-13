package com.durandsuppicich.danmsusuarios.mapper;

import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.ConstructionType;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionDetailsDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPostDto;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPutDto;

public class ConstructionMapper implements IConstructionMapper {

    @Override
    public Construction map(ConstructionPostDto constructionDto) {

        Construction construction = new Construction();
        Customer customer = new Customer();
        ConstructionType constructionType = new ConstructionType();

        customer.setId(constructionDto.getCustomerId());

        constructionType.setId(constructionDto.getConstructionTypeId());

        construction.setDescription(constructionDto.getDescription());
        construction.setLatitude(constructionDto.getLatitude());
        construction.setLongitude(constructionDto.getLongitude());
        construction.setAddress(constructionDto.getAddress());
        construction.setArea(constructionDto.getArea());
        construction.setConstructionType(constructionType);
        construction.setCustomer(customer);

        return construction;
    }

    @Override
    public Construction map(ConstructionPutDto constructionDto) {

        Construction construction = new Construction();

        construction.setId(constructionDto.getId());
        construction.setDescription(constructionDto.getDescription());
        construction.setLatitude(constructionDto.getLatitude());
        construction.setLongitude(constructionDto.getLongitude());
        construction.setAddress(constructionDto.getDescription());
        construction.setArea(constructionDto.getArea());

        return construction;
    }

    @Override
    public ConstructionDto mapToDto(Construction construction) {

        ConstructionDto dto = new ConstructionDto();

        dto.setId(construction.getId());
        dto.setDescription(construction.getDescription());
        dto.setAddress(construction.getDescription());
        dto.setCustomerBusinessName(construction.getCustomer().getBusinessName());

        return dto;
    }

    @Override
    public ConstructionDetailsDto mapToDetailsDto(Construction construction) {
        return null;
    }


}
