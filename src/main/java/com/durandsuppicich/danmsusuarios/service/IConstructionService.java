package com.durandsuppicich.danmsusuarios.service;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Construction;

public interface IConstructionService {

    Construction post(Construction construction);

    List<Construction> getAll();

    Construction getById(Integer id);

    List<Construction> getByCustomerOrConstructionType(Integer customerId, Integer constructionTypeId);

    List<Construction> getByCuit(String cuit);

    void put(Construction construction, Integer id);

    void delete(Integer id);

}
