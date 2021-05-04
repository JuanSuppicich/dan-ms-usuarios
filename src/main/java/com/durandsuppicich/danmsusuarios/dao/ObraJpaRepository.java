package com.durandsuppicich.danmsusuarios.dao;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ObraJpaRepository extends JpaRepository<Obra, Integer> {

    @Query("SELECT DISTINCT o FROM Obra o WHERE (:idCliente is null or o.cliente.id = :idCliente) and (:idTipoObra is null or o.tipoObra.id = :idTipoObra)")
    List<Obra> findByIdClienteOrIdTipoObra(@Param("idCliente") Integer idCliente,  @Param("idTipoObra") Integer idTipoObra);

}
