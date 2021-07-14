package com.durandsuppicich.danmsusuarios.service;

import java.time.Instant;
import java.util.List;

import com.durandsuppicich.danmsusuarios.exception.customer.BusinessNameNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.customer.CuitNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.customer.IdConstructionNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.customer.IdNotFoundException;
import com.durandsuppicich.danmsusuarios.exception.validation.IdsNotMatchException;
import com.durandsuppicich.danmsusuarios.repository.ICustomerJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.User;
import com.durandsuppicich.danmsusuarios.domain.UserType;

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

        customer.setPostDate(Instant.now());

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

        return customerRepository.getAll();
            //.stream()
            //.filter(c -> c.getDeleteDate() == null)
            //.collect(Collectors.toList());
    }

    @Override
    public Customer getById(Integer id) {

        return customerRepository.findByIdAndDeleteDateIsNull(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public Customer getByCuit(String cuit) {

        return customerRepository.findByCuitAndDeleteDateIsNull(cuit)
                .orElseThrow(() -> new CuitNotFoundException(cuit));
    }

    @Override
    public Customer getByBusinessName(String businessName) {

        return customerRepository.findByBusinessName(businessName)
                .orElseThrow(() -> new BusinessNameNotFoundException(businessName));
    }

    @Override
    public Customer getByConstructionId(Integer constructionId) {

        return customerRepository.findByConstructionId(constructionId)
                .orElseThrow(() -> new IdConstructionNotFoundException(constructionId));
    }

    @Override
    public void put(Customer customer, Integer id) {

        if (!customer.getId().equals(id)) throw new IdsNotMatchException();

        Customer oldCustomer = customerRepository.findByIdAndDeleteDateIsNull(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        //set fecha actualizacion
        //Update
        customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        if (!orderService.getAll(customer).isEmpty()) { //TODO improve this

            customer.setDeleteDate(Instant.now()); //TODO improve this
            customerRepository.save(customer);

        } else {
            customerRepository.deleteById(id);
        }
    }
}