package com.durandsuppicich.danmsusuarios.dao;

import com.durandsuppicich.danmsusuarios.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer> {
}
