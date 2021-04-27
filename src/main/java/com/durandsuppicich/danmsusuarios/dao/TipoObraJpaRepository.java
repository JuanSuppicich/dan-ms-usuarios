package com.durandsuppicich.danmsusuarios.dao;

import com.durandsuppicich.danmsusuarios.domain.TipoObra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoObraJpaRepository extends JpaRepository<TipoObra, Integer> { }
