package com.durandsuppicich.danmsusuarios.service;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.Order;

public interface IOrderService {

    List<Order> getAll(Customer customer);

}
