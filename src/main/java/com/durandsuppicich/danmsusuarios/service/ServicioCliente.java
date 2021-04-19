package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

import org.springframework.stereotype.Service;

@Service
public class ServicioCliente implements IServicioCliente {

    private Integer ID_GEN = 1;
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private final IServicioRiesgoCrediticio servicioRiesgo;
    private final IServicioPedido servicioPedido;


    public ServicioCliente(IServicioRiesgoCrediticio servicioRiesgo, IServicioPedido servicioPedido) {
        this.servicioRiesgo = servicioRiesgo;
        this.servicioPedido = servicioPedido;
    }
    
    @Override
    public Cliente crear(Cliente cliente) {
        if (servicioRiesgo.resporteBCRAPositivo(cliente.getCuit())) {
            cliente.setHabilitadoOnline(true);
        }
        cliente.setId(ID_GEN++);
        clientes.add(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> todos() {
        return clientes
                .stream()
                .filter(c -> c.getFechaBaja() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> clientePorId(Integer id) {
        return clientes
            .stream()
            .filter(c -> c.getId().equals(id) && c.getFechaBaja() == null)
            .findFirst();
    }

    @Override
    public Optional<Cliente> clientePorCuit(String cuit) {
        return clientes
            .stream()
            .filter(c -> c.getCuit().equals(cuit) && c.getFechaBaja() == null)
            .findFirst();
    }

    @Override
    public Optional<Cliente> clientePorRazonSocial(String razonSocial) {
        return clientes
            .stream()
            .filter(c -> c.getRazonSocial().equals(razonSocial) && c.getFechaBaja() == null)
            .findFirst();
    }

    @Override
    public void actualizar(Integer id, Cliente cliente) {
        OptionalInt indexOpt =   IntStream.range(0, clientes.size())
            .filter(i -> clientes.get(i).getId().equals(id))
            .findFirst();

        if (indexOpt.isPresent()) {
            clientes.set(indexOpt.getAsInt(), cliente);
        } 
        //Agregar codigo de excepciones 
    }
    
    @Override
    public void eliminar(Integer id) {
        OptionalInt indexOpt =   IntStream.range(0, clientes.size())
        .filter(i -> clientes.get(i).getId().equals(id))
        .findFirst();

        if (indexOpt.isPresent()) {
            Cliente cliente = clientes.get(indexOpt.getAsInt());
            if (servicioPedido.obtenerPedidos(cliente).isEmpty()) {
                cliente.setFechaBaja(Instant.now());
            }
            else {
                clientes.remove(indexOpt.getAsInt());
            }
        }
        //Agregar codigo de excepciones
    }

   

}