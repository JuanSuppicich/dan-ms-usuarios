package com.durandsuppicich.danmsusuarios.service;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Pedido;

import org.springframework.stereotype.Service;

@Service
public class ServicioPedido implements IServicioPedido {

    @Override
    public List<Pedido> obtenerPedidos(Cliente cliente) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido());
        return pedidos;
    }

}
