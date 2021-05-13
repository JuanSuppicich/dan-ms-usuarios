package com.durandsuppicich.danmsusuarios.dao;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByCuit(String cuit);

    Optional<Cliente> findByRazonSocial(String razonSocial);

    @Query("SELECT c FROM Cliente c LEFT JOIN c.obras o WHERE o.id = :idObra")
    Optional<Cliente> findByIdObra(@Param("idObra") Integer idObra);

}
