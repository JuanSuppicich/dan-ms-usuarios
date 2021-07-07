package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Construction;

public interface IConstructionService {

    Construction post(Construction construction);

    List<Construction> getAll();

    Optional<Construction> getById(Integer id);

    List<Construction> getByCustomerOrConstructionType(Integer customerId, Integer constructionTypeId);

    void put(Construction construction, Integer id);

    void delete(Integer id);

}
