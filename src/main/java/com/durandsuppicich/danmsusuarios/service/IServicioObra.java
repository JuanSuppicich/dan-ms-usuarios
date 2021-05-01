package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Obra;

public interface IServicioObra {

    Obra crear(Obra obra);
    List<Obra> todos();
    Optional<Obra> obraPorId(Integer id);
    List<Obra> obrasPorClienteOTipoObra(Integer idCliente, Integer idTipoObra);
    void actualizar(Obra obra, Integer id);
    void eliminar(Integer id);

}
