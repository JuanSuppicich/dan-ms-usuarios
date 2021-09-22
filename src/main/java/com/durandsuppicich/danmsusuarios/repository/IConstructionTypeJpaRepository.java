package com.durandsuppicich.danmsusuarios.repository;

import com.durandsuppicich.danmsusuarios.domain.ConstructionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConstructionTypeJpaRepository extends JpaRepository<ConstructionType, Integer> {
}
