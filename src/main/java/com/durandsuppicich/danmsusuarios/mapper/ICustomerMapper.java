package com.durandsuppicich.danmsusuarios.mapper;

import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerDetailsDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPostDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPutDto;

public interface ICustomerMapper {

    Customer map(CustomerPostDto customerDto);

    Customer map(CustomerPutDto customerDto);

    CustomerDto mapToDto(Customer customer);

    CustomerDetailsDto mapToDetailsDto(Customer customer);

}
