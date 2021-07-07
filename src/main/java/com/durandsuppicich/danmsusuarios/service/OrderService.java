package com.durandsuppicich.danmsusuarios.service;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.Order;

import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Override
    public List<Order> getAll(Customer customer) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }

}
