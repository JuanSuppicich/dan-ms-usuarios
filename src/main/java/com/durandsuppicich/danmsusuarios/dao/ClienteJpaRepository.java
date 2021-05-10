package com.durandsuppicich.danmsusuarios.dao;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByCuit(String cuit);

    Optional<Cliente> findByRazonSocial(String razonSocial);

}
