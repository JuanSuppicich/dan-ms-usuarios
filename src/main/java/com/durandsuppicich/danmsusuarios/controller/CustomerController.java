package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPostDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPutDto;
import com.durandsuppicich.danmsusuarios.exception.http.NotFoundException;
import com.durandsuppicich.danmsusuarios.mapper.ICustomerMapper;
import com.durandsuppicich.danmsusuarios.service.ICustomerService;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/customer")
@Api(value = "CustomerController")
public class CustomerController {

    private final ICustomerService customerService;

    private final ICustomerMapper customerMapper;

    public CustomerController(ICustomerService customerService,
                              ICustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new customer")
    public ResponseEntity<Customer> post(@RequestBody @Validated CustomerPostDto customerDto) {

        Customer customer = customerMapper.map(customerDto);
        Customer body = customerService.post(customer);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all customers")
    public ResponseEntity<List<CustomerDto>> getAll() {

        List<Customer> customers = customerService.getAll();
        List<CustomerDto> body = customerMapper.mapToDto(customers);

        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves a customer based on the given id")
    public ResponseEntity<CustomerDto> getById(@PathVariable
                                                   @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        Customer customer = customerService.getById(id);
        CustomerDto body = customerMapper.mapToDto(customer);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = "cuit")
    @ApiOperation(value = "Retrieves a customer based on the given cuit")
    public ResponseEntity<CustomerDto> getByCuit(@RequestParam(name = "cuit")
                                                     @NotBlank
                                                     @Length(min = 11, max = 11) String cuit) {

        Optional<Customer> customer = customerService.getByCuit(cuit);

        if (customer.isPresent()) {
            CustomerDto body = customerMapper.mapToDto(customer.get());
            return ResponseEntity.ok(body);
        } else {
            throw new NotFoundException("Customer no encontrado. Cuit: " + cuit);//TODO change this
        }
    }

    @GetMapping(params = "businessName")
    @ApiOperation(value = "Retrieves a customer based on the given business name")
    public ResponseEntity<CustomerDto> getByBusinessName(
            @RequestParam(name = "businessName", required = false)
                    @Length(max = 32) String businessName) {

        Optional<Customer> customer = customerService.getByBusinessName(businessName);

        if (customer.isPresent()) {
            CustomerDto body = customerMapper.mapToDto(customer.get());
            return ResponseEntity.ok(body);
        } else {
            throw new NotFoundException("Customer no encontrado. Razon social: " + businessName); //TODO change this
        }
    }

    @GetMapping(params = "constructionId")
    @ApiOperation(value = "Retrieves a customer based on the given construction site id")
    public ResponseEntity<CustomerDto> getByConstructionId(
            @RequestParam(name = "constructionId")
                @Range(min = 1, max = Integer.MAX_VALUE) Integer constructionId) {

        Optional<Customer> customer = customerService.getByConstructionId(constructionId);

        if (customer.isPresent()) {
            CustomerDto body = customerMapper.mapToDto(customer.get());
            return ResponseEntity.ok(body);
        } else {
            throw new NotFoundException("Customer no encontrado. Id Construction: " + constructionId); //TODO change this
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a customer based on the given id")
    public ResponseEntity<Customer> put(@RequestBody @Validated CustomerPutDto customerDto,
                                        @PathVariable @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        Customer customer = customerMapper.map(customerDto);

        customerService.put(customer, id); //TODO change validacion coincidencia id
        return ResponseEntity.ok().build(); //TODO Change response
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a customer based on the given id")
    public ResponseEntity<Customer> delete(@PathVariable @Range(min = 1, max = Integer.MAX_VALUE) Integer id) {

        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
