package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

public interface IServicioCliente {

    Cliente crear(Cliente cliente); //Agregar exepciones 
    List<Cliente> todos();
    Optional<Cliente> clientePorId(Integer id);
    Optional<Cliente> clientePorCuit(String cuit);
    Optional<Cliente> clientePorRazonSocial(String razonSocial);
    void actualizar(Integer id, Cliente cliente);
    void eliminar(Integer id);

}
