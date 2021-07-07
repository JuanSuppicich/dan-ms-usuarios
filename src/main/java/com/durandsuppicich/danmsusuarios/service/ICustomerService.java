package com.durandsuppicich.danmsusuarios.service;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Customer;

public interface ICustomerService {

    Customer post(Customer customer);

    List<Customer> getAll();

    Optional<Customer> getById(Integer id);

    Optional<Customer> getByCuit(String cuit);

    Optional<Customer> getByBusinessName(String businessName);

    Optional<Customer> getByConstructionId(Integer constructionId);

    void put(Customer customer, Integer id);

    void delete(Integer id);

}
