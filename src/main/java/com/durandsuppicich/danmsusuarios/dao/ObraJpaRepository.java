package com.durandsuppicich.danmsusuarios.dao;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraJpaRepository extends JpaRepository<Obra, Integer> { 

    List<Obra> findByClienteOrTipoObra(Integer idCliente, Integer idTipoObra);

}
