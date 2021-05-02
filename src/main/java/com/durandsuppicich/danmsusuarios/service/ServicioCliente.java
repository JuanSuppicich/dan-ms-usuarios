package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.durandsuppicich.danmsusuarios.dao.ClienteJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ServicioCliente implements IServicioCliente {

    private final IServicioRiesgoCrediticio servicioRiesgo;
    private final IServicioPedido servicioPedido;
    private final ClienteJpaRepository clienteRepository;

    public ServicioCliente(IServicioRiesgoCrediticio servicioRiesgo, IServicioPedido servicioPedido,
            ClienteJpaRepository clienteRepository) {
        this.servicioRiesgo = servicioRiesgo;
        this.servicioPedido = servicioPedido;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crear(Cliente cliente) {

        if (servicioRiesgo.resporteBCRAPositivo(cliente.getCuit())) {
            cliente.setHabilitadoOnline(true);
        } else {
            cliente.setHabilitadoOnline(false);
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> todos() {

        return StreamSupport
            .stream(clienteRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
            .stream()
            .filter(c -> c.getFechaBaja() == null)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> clientePorId(Integer id) {

        Optional<Cliente> optCliente = clienteRepository.findById(id);

        if (optCliente.isPresent() && optCliente.get().getFechaBaja() == null) {
            return optCliente;
        }

        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Cliente> clientePorCuit(String cuit) {

        Optional<Cliente> optCliente = clienteRepository.findByCuit(cuit);

        if (optCliente.isPresent() && optCliente.get().getFechaBaja() == null) {
            return optCliente;
        }

        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Cliente> clientePorRazonSocial(String razonSocial) {

        Optional<Cliente> optCliente = clienteRepository.findByRazonSocial(razonSocial);

        if (optCliente.isPresent() && optCliente.get().getFechaBaja() == null) {
            return optCliente;
        }

        return Optional.ofNullable(null);
    }

    @Override
    public void actualizar(Integer id, Cliente cliente) {

        if (clienteRepository.existsById(id)) {
            clienteRepository.save(cliente);
        } else {
            throw new NotFoundException("Cliente inexistente. Id: " + id);
        }
    }

    @Override
    public void eliminar(Integer id) {

        if (clienteRepository.existsById(id)) {

            Optional<Cliente> optCliente = clienteRepository.findById(id);

            if (!servicioPedido.obtenerPedidos(optCliente.get()).isEmpty()) {

                optCliente.get().setFechaBaja(Instant.now());
                clienteRepository.save(optCliente.get());

            } else {
                clienteRepository.deleteById(id);
            }
        } else {
            throw new NotFoundException("Cliente inexistente. Id: " + id);
        }
    }
}