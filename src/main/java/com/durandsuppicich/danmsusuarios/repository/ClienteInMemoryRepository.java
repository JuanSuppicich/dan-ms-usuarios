package com.durandsuppicich.danmsusuarios.repository;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

import org.springframework.stereotype.Repository;

import frsf.isi.dan.InMemoryRepository;

@Repository
public class ClienteInMemoryRepository extends InMemoryRepository<Cliente>{

    @Override
    public Integer getId(Cliente entity) {
        return entity.getId();
    }

    @Override
    public void setId(Cliente entity, Integer id) {
        entity.setId(id);
        
    }
}
