package com.durandsuppicich.danmsusuarios.controller;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.exception.BadRequestException;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;
import com.durandsuppicich.danmsusuarios.service.ICustomerService;

import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/customer")
@Api(value = "CustomerController")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new customer")
    public ResponseEntity<Customer> post(@RequestBody Customer customer) {

        if (customer.getConstructions() != null && !customer.getConstructions().isEmpty()) {

            boolean constructionTypeOk =
                    customer.getConstructions().stream().allMatch(o -> o.getConstructionType() != null);

            if (constructionTypeOk) {

                Customer body = customerService.post(customer);
                return ResponseEntity.ok(body);
    
            } else {
                throw new BadRequestException("Tipo de obra: " + customer.getConstructions()); //TODO change this
            }
        } else {
            throw new BadRequestException("Obras: " + customer.getConstructions()); //TODO change this
        }
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all customers")
    public ResponseEntity<List<Customer>> getAll() {

        List<Customer> body = customerService.getAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieves a customer based on the given id")
    public ResponseEntity<Customer> getById(@PathVariable Integer id) {

        Optional<Customer> body = customerService.getById(id);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Customer no encontrado. Id: " + id); //TODO change this
        }
    }

    @GetMapping(params = "cuit")
    @ApiOperation(value = "Retrieves a customer based on the given cuit")
    public ResponseEntity<Customer> getByCuit(@RequestParam(name = "cuit") String cuit) {

        Optional<Customer> body = customerService.getByCuit(cuit);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Customer no encontrado. Cuit: " + cuit);//TODO change this
        }
    }

    @GetMapping(params = "businessName")
    @ApiOperation(value = "Retrieves a customer based on the given business name")
    public ResponseEntity<Customer> getByBusinessName(
            @RequestParam(name = "businessName", required = false) String businessName) {

        Optional<Customer> body = customerService.getByBusinessName(businessName);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Customer no encontrado. Razon social: " + businessName); //TODO change this
        }
    }

    @GetMapping(params = "constructionId")
    @ApiOperation(value = "Retrieves a customer based on the given construction site id")
    public ResponseEntity<Customer> getByConstructionId(
            @RequestParam(name = "constructionId") Integer constructionId) {

        Optional<Customer> body = customerService.getByConstructionId(constructionId);

        if (body.isPresent()) {
            return ResponseEntity.ok(body.get());
        } else {
            throw new NotFoundException("Customer no encontrado. Id Construction: " + constructionId); //TODO change this
        }
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Updates a customer based on the given id")
    public ResponseEntity<Customer> put(@RequestBody Customer customer, @PathVariable Integer id) {

        customerService.put(customer, id); //TODO change validacion coincidencia id
        return ResponseEntity.ok().build(); //TODO Change response
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a customer based on the given id")
    public ResponseEntity<Customer> delete(@PathVariable Integer id) {

        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
