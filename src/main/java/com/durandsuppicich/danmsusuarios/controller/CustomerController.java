package com.durandsuppicich.danmsusuarios.controller;

import java.net.URI;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.dto.OnCustomerPost;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPostDto;
import com.durandsuppicich.danmsusuarios.dto.customer.CustomerPutDto;
import com.durandsuppicich.danmsusuarios.exception.validation.IdsNotMatchException;
import com.durandsuppicich.danmsusuarios.mapper.ICustomerMapper;
import com.durandsuppicich.danmsusuarios.service.ICustomerService;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Mod11Check;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@RestController
@Validated
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
    @Validated(OnCustomerPost.class)
    @ApiOperation(value = "Creates a new customer")
    public ResponseEntity<CustomerDto> post(@RequestBody @Valid CustomerPostDto customerDto) {

        Customer customer = customerMapper.map(customerDto);
        Customer result = customerService.post(customer);
        CustomerDto body = customerMapper.mapToDto(result);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();

        return ResponseEntity.created(location).body(body);
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
                                                   @Positive Integer id) {

        Customer customer = customerService.getById(id);
        CustomerDto body = customerMapper.mapToDto(customer);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = "cuit")
    @ApiOperation(value = "Retrieves a customer based on the given cuit")
    public ResponseEntity<CustomerDto> getByCuit(@RequestParam(name = "cuit")
                                                     @NotBlank
                                                     @Length(min = 11, max = 11)
                                                     @Mod11Check(threshold = 7) String cuit) {

        Customer customer = customerService.getByCuit(cuit);
        CustomerDto body = customerMapper.mapToDto(customer);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = "businessName")
    @ApiOperation(value = "Retrieves a customer based on the given business name")
    public ResponseEntity<CustomerDto> getByBusinessName(
            @RequestParam(name = "businessName", required = false)
                    @Length(max = 32) String businessName) {

        Customer customer = customerService.getByBusinessName(businessName);
        CustomerDto body = customerMapper.mapToDto(customer);

        return ResponseEntity.ok(body);
    }

    @GetMapping(params = "constructionId")
    @ApiOperation(value = "Retrieves a customer based on the given construction site id")
    public ResponseEntity<CustomerDto> getByConstructionId(
            @RequestParam(name = "constructionId")
                @Positive Integer constructionId) {

        Customer customer = customerService.getByConstructionId(constructionId);
        CustomerDto body = customerMapper.mapToDto(customer);

        return ResponseEntity.ok(body);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a customer based on the given id")
    public ResponseEntity<?> put(@RequestBody @Valid CustomerPutDto customerDto,
                                        @PathVariable @Positive Integer id) {

        if (!customerDto.getId().equals(id))
            throw new IdsNotMatchException(customerDto.getId(), id);

        Customer customer = customerMapper.map(customerDto);
        customerService.put(customer, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a customer based on the given id")
    public ResponseEntity<?> delete(@PathVariable @Positive Integer id) {

        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
