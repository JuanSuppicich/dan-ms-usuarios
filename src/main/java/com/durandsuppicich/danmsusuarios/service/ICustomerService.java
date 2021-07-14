package com.durandsuppicich.danmsusuarios.service;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Customer;

public interface ICustomerService {

    Customer post(Customer customer);

    List<Customer> getAll();

    Customer getById(Integer id);

    Customer getByCuit(String cuit);

    Customer getByBusinessName(String businessName);

    Customer getByConstructionId(Integer constructionId);

    void put(Customer customer, Integer id);

    void delete(Integer id);

}
