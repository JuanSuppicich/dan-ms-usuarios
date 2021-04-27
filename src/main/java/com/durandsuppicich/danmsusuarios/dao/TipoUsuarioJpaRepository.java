package com.durandsuppicich.danmsusuarios.dao;

import com.durandsuppicich.danmsusuarios.domain.TipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioJpaRepository extends JpaRepository<TipoUsuario, Integer> { }
