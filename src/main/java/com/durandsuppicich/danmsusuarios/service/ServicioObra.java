package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.durandsuppicich.danmsusuarios.dao.ObraJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ServicioObra implements IServicioObra {

    private final ObraJpaRepository obraRepository;

    public ServicioObra(ObraJpaRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    @Override
    public Obra crear(Obra obra) {
        return obraRepository.save(obra);
    }

    @Override
    public List<Obra> todos() {
        return obraRepository.findAll();
    }

    @Override
    public Optional<Obra> obraPorId(Integer id) {
        return obraRepository.findById(id);
    }

    @Override
    public List<Obra> obrasPorClienteOTipoObra(Integer idCliente, Integer idTipoObra) {

        List<Obra> obras = obraRepository.findAll(); // Se puede mejorar con una consulta jpql

        if (idCliente != null) {
            obras = obras
                .stream()
                .filter(o -> o.getCliente().getId().equals(idCliente))
                .collect(Collectors.toList());
        }
        if (idTipoObra != null) {
            obras = obras
                .stream()
                .filter(o -> o.getTipoObra().getId().equals(idTipoObra))
                .collect(Collectors.toList());
        }
        return obras;
    }

    @Override
    public void actualizar(Obra obra, Integer id) {

        if (obraRepository.existsById(id)) {
            obraRepository.save(obra);
        } else {
            throw new NotFoundException("Obra inexistente. Id: " + id);
        }
    }

    @Override
    public void eliminar(Integer id) {

        if (obraRepository.existsById(id)) {
            obraRepository.deleteById(id);
        } else {
            throw new NotFoundException("Obra inexistente. Id: " + id);
        }
    }

}
