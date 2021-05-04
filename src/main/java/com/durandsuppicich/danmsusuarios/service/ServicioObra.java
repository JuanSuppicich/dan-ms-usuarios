package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

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
        return obraRepository.findByIdClienteOrIdTipoObra(idCliente, idTipoObra);
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
