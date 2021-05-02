package com.durandsuppicich.danmsusuarios.service;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Pedido;

public interface IServicioPedido {

    List<Pedido> obtenerPedidos(Cliente cliente);

}
