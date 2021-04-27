package com.durandsuppicich.danmsusuarios.dao;

import com.durandsuppicich.danmsusuarios.domain.Obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraJpaRepository extends JpaRepository<Obra, Integer> { }
