package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.util.List;

import com.durandsuppicich.danmsusuarios.exception.construction.ConstructionIdNotFoundException;
import com.durandsuppicich.danmsusuarios.repository.IConstructionJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Construction;

import org.springframework.stereotype.Service;

@Service
public class ConstructionService implements IConstructionService {

    private final IConstructionJpaRepository constructionRepository;

    public ConstructionService(IConstructionJpaRepository constructionRepository) {
        this.constructionRepository = constructionRepository;
    }

    @Override
    public Construction post(Construction construction) {
        construction.setPostDate(Instant.now());
        return constructionRepository.save(construction);
    }

    @Override
    public List<Construction> getAll() {
        return constructionRepository.findAll();
    }

    @Override
    public Construction getById(Integer id) {
        return constructionRepository.findById(id)
                .orElseThrow(() -> new ConstructionIdNotFoundException(id));
    }

    @Override
    public List<Construction> getByCustomerOrConstructionType(Integer customerId, Integer constructionTypeId) {
        return constructionRepository.findByCustomerIdOrConstructionId(customerId, constructionTypeId);
    }

    @Override
    public void put(Construction construction, Integer id) {

        constructionRepository.findById(id)
                .map (c ->  {
                    c.setPutDate(Instant.now());
                    c.setDescription(construction.getDescription());
                    c.setLatitude(construction.getLatitude());
                    c.setLongitude(construction.getLongitude());
                    c.setAddress(construction.getAddress());
                    c.setArea(construction.getArea());
                    return constructionRepository.save(c);
                })
                .orElseThrow(() -> new ConstructionIdNotFoundException(id));
    }

    @Override
    public void delete(Integer id) {

        if (constructionRepository.existsById(id)) {

            constructionRepository.deleteById(id);

        } else {
            throw new ConstructionIdNotFoundException(id);
        }
    }

}
