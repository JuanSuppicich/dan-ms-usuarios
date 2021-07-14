package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.repository.IConstructionJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.exception.http.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ConstructionService implements IConstructionService {

    private final IConstructionJpaRepository constructionRepository;

    public ConstructionService(IConstructionJpaRepository constructionRepository) {
        this.constructionRepository = constructionRepository;
    }

    @Override
    public Construction post(Construction construction) {
        return constructionRepository.save(construction);
    }

    @Override
    public List<Construction> getAll() {
        return constructionRepository.findAll();
    }

    @Override
    public Optional<Construction> getById(Integer id) {
        return constructionRepository.findById(id);
    }

    @Override
    public List<Construction> getByCustomerOrConstructionType(Integer customerId, Integer constructionTypeId) {
        return constructionRepository.findByCustomerIdOrConstructionId(customerId, constructionTypeId);
    }

    @Override
    public void put(Construction construction, Integer id) {

        if (constructionRepository.existsById(id)) {
            constructionRepository.save(construction);
        } else {
            throw new NotFoundException("Construction inexistente. Id: " + id); //TODO change this
        }
    }

    @Override
    public void delete(Integer id) {

        if (constructionRepository.existsById(id)) {
            constructionRepository.deleteById(id);
        } else {
            throw new NotFoundException("Construction inexistente. Id: " + id); //TODO change this
        }
    }

}
