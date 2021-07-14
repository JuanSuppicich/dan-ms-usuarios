package com.durandsuppicich.danmsusuarios.mapper;

import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPostDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerDetailsDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPostDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPutDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements ICustomerMapper {

    private final IConstructionMapper constructionMapper;

    public CustomerMapper(IConstructionMapper constructionMapper) {
        this.constructionMapper = constructionMapper;
    }

    @Override
    public Customer map(CustomerPostDto customerDto) {

        Customer customer = new Customer();

        for(ConstructionPostDto constructionDto : customerDto.getConstructions()) {

            Construction construction = constructionMapper.map(constructionDto);

            customer.addConstruction(construction);
        }

        customer.setBusinessName(customer.getBusinessName());
        customer.setCuit(customer.getCuit());
        customer.setEmail(customer.getEmail());
        customer.setMaxCurrentAccount(customer.getMaxCurrentAccount());

        return customer;
    }

    @Override
    public Customer map(CustomerPutDto customerDto) {

        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setBusinessName(customerDto.getBusinessName());
        customer.setEmail(customerDto.getEmail());
        customer.setMaxCurrentAccount(customerDto.getMaxCurrentAccount());

        return customer;
    }

    @Override
    public CustomerDto mapToDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setBusinessName(customer.getBusinessName());
        customerDto.setCuit(customer.getCuit());
        customerDto.setEmail(customer.getEmail());

        return customerDto;
    }

    @Override
    public CustomerDetailsDto mapToDetailsDto(Customer customer) {
        return null;
    }

    @Override
    public List<CustomerDto> mapToDto(List<Customer> customers) {

        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            customerDtos.add(mapToDto(customer));
        }

        return customerDtos;
    }


}
