package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.durandsuppicich.danmsusuarios.repository.ICustomerJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.User;
import com.durandsuppicich.danmsusuarios.domain.UserType;
import com.durandsuppicich.danmsusuarios.exception.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    private final ICreditRiskService riskService;

    private final IOrderService orderService;
    
    private final ICustomerJpaRepository customerRepository;

    public CustomerService(ICreditRiskService riskService,
                           IOrderService orderService,
                           ICustomerJpaRepository customerRepository) {
        this.riskService = riskService;
        this.orderService = orderService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer post(Customer customer) {

        if (riskService.BcraPositiveReport(customer.getCuit())) {
            customer.setAllowedOnline(true);
        } else {
            customer.setAllowedOnline(false);
        }

        UserType userType = new UserType(1, "Customer");
        User user = new User(customer.getEmail(), "1234", userType);
        customer.setUser(user);

        //We need to set customer attribute in each construction object
        // in order to save the relationship
        //TODO improve
        List<Construction> aux = List.copyOf(customer.getConstructions());
        aux
            .stream()
            .forEach( (o) -> {
                customer.addConstruction(o);
            });
        customer.setConstructions(aux);

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {

        return customerRepository.findAll()
            .stream()
            .filter(c -> c.getDeleteDate() == null)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> getById(Integer id) {

        Optional<Customer> optCustomer = customerRepository.findById(id); //TODO improve! throw notFoundException

        if (optCustomer.isPresent() && optCustomer.get().getDeleteDate() == null) {
            return optCustomer;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Customer> getByCuit(String cuit) {

        Optional<Customer> optCustomer = customerRepository.findByCuit(cuit);

        if (optCustomer.isPresent() && optCustomer.get().getDeleteDate() == null) {
            return optCustomer;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Customer> getByBusinessName(String businessName) {

        Optional<Customer> optCustomer = customerRepository.findByBusinessName(businessName);

        if (optCustomer.isPresent() && optCustomer.get().getDeleteDate() == null) {
            return optCustomer;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Customer> getByConstructionId(Integer constructionId) {
        return customerRepository.findByConstructionId(constructionId);
    }

    @Override
    public void put(Customer customer, Integer id) {

        if (customerRepository.existsById(id)) {
            customerRepository.save(customer);
        } else {
            throw new NotFoundException("Customer inexistente. Id: " + id);//TODO change this
        }
    }

    @Override
    public void delete(Integer id) {

        if (customerRepository.existsById(id)) {

            Optional<Customer> optCustomer = customerRepository.findById(id);

            if (!orderService.getAll(optCustomer.get()).isEmpty()) { //TODO improve this

                optCustomer.get().setDeleteDate(Instant.now().truncatedTo(ChronoUnit.MILLIS)); //TODO improve this
                customerRepository.save(optCustomer.get());

            } else {
                customerRepository.deleteById(id);
            }
        } else {
            throw new NotFoundException("Customer inexistente. Id: " + id); //TODO change this
        }
    }
}