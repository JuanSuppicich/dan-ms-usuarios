package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.client.OrderDto;
import com.durandsuppicich.danmsusuarios.exception.customer.CustomerBusinessNameNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.customer.CustomerCuitNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.customer.ConstructionIdNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.customer.CustomerIdNotFoundException;
import com.durandsuppicich.danmsusuarios.repository.ICustomerJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.User;
import com.durandsuppicich.danmsusuarios.domain.UserType;

import com.durandsuppicich.danmsusuarios.client.IOrderClient;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    private final ICreditRiskService riskService;

    private final ICustomerJpaRepository customerRepository;

    private final IOrderClient orderClient;

    private final CircuitBreakerFactory circuitBreakerFactory;

    public CustomerService(ICreditRiskService riskService,
                           ICustomerJpaRepository customerRepository,
                           IOrderClient orderClient,
                           CircuitBreakerFactory circuitBreakerFactory) {
        this.riskService = riskService;
        this.orderClient = orderClient;
        this.customerRepository = customerRepository;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Override
    public Customer post(Customer customer) {

        customer.setPostDate(Instant.now());

        if (riskService.BcraPositiveReport(customer.getCuit())) {
            customer.setAllowedOnline(true);
        } else {
            customer.setAllowedOnline(false);
        }

        UserType userType = new UserType(1, "Customer");
        User user = new User(customer.getEmail(), "1234", userType);
        customer.setUser(user);

        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {

        return customerRepository.getAll();
    }

    @Override
    public Customer getById(Integer id) {

        return customerRepository.findByIdAndDeleteDateIsNull(id)
                .orElseThrow(() -> new CustomerIdNotFoundException(id));
    }

    @Override
    public Customer getByCuit(String cuit) {

        return customerRepository.findByCuitAndDeleteDateIsNull(cuit)
                .orElseThrow(() -> new CustomerCuitNotFoundException(cuit));
    }

    @Override
    public Customer getByBusinessName(String businessName) {

        return customerRepository.findByBusinessNameAndDeleteDateIsNull(businessName)
                .orElseThrow(() -> new CustomerBusinessNameNotFoundException(businessName));
    }

    @Override
    public Customer getByConstructionId(Integer constructionId) {

        return customerRepository.findByConstructionId(constructionId)
                .orElseThrow(() -> new ConstructionIdNotFoundException(constructionId));
    }

    @Override
    public void put(Customer customer, Integer id) {

        customerRepository.findByIdAndDeleteDateIsNull(id)
               .map(c -> {
                   c.setPutDate(Instant.now());
                   c.setBusinessName(customer.getBusinessName());
                   c.setEmail(customer.getEmail());
                   c.setMaxCurrentAccount(customer.getMaxCurrentAccount());
                   return customerRepository.save(c);
               })
               .orElseThrow(() -> new CustomerIdNotFoundException(id));
    }

    @Override
    public void delete(Integer id) {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuit-breaker");

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerIdNotFoundException(id));

        List<OrderDto> orders = circuitBreaker.run(
                () -> orderClient.getByCuit(customer.getCuit()),
                throwable -> defaultOrderList()
        );

        if (orders.isEmpty()) {
            customerRepository.deleteById(id);
        } else {
            customer.setDeleteDate(Instant.now());
            customerRepository.save(customer);
        }
    }

    // This method returns a non-empty list, so if service call fails customer will be logically deleted.
    public List<OrderDto> defaultOrderList() {
        List<OrderDto> defaultOrderList = new ArrayList<>();
        defaultOrderList.add(new OrderDto());
        return defaultOrderList;
    }
}