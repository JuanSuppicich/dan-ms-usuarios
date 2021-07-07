package com.durandsuppicich.danmsusuarios.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.*;
import com.durandsuppicich.danmsusuarios.domain.Customer;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsUsuariosApplicationTests.class)
@Profile("testing")
public class CustomerJpaRepositoryTest {

    @Autowired
    ICustomerJpaRepository customerRepository;

    @Test
    public void repositoryExists() {
        assertNotNull(customerRepository);
    }
    
    @Test
    public void save_CustomerOk_CustomerCreated() {
        //TODO improve this
        UserType userType = new UserType();
        userType.setId(1);
        User user = new User("user5", "password5", userType);
        ConstructionType constructionType = new ConstructionType();
        constructionType.setId(1);
        Construction construction =
                new Construction("description3", Float.valueOf("3.0"), Float.valueOf("3.0"), "address3", 30, constructionType);
        List<Construction> constructions = new ArrayList<>();
        constructions.add(construction);
        Customer customer =
                new Customer("customer3", "3333", "customer3@emai.com", 3000.0, false, null, user, constructions);

        Customer result = customerRepository.save(customer);

        assertThat(customerRepository.count(), is(equalTo(3L)));
        assertThat(result.getId(), is(equalTo(3)));
        assertThat(result.getUser(), notNullValue());
        assertThat(result.getUser().getId(), is(equalTo(5)));
    }

    @Test
    public void save_CustomerWithoutConstruction_CustomerCreated() {
        
        UserType userType = new UserType("Customer");
        userType.setId(1);
        User user = new User("user6", "password6", userType);
        ConstructionType constructionType = new ConstructionType("House"); //TODO change this
        constructionType.setId(1);
        Customer customer =
                new Customer("customer4", "4444", "customer4@emai.com", 4000.0, false, null, user, null);

        customerRepository.save(customer);

        assertThat(customerRepository.count(), is(equalTo(4L)));
    }

    @Sql({"/test_data.sql"})
    @Test
    public void findById_CustomerById_CustomerRetrievedWithUser() {

        Optional<Customer> customer = customerRepository.findById(2);

        assertTrue(customer.isPresent());
        assertThat(customer.get().getId(), is(equalTo(2)));
        assertThat(customer.get().getUser(), notNullValue());
        assertThrows(LazyInitializationException.class, () -> {
            customer.get().getConstructions().get(0);
        });
    }

    @Test
    public void findByCuit_CustomerByCuit_CustomerRetrievedWithUser() {

        Optional<Customer> customer = customerRepository.findByCuit("2222");

        assertTrue(customer.isPresent());
        assertThat(customer.get().getId(), is(equalTo(2)));
        assertThat(customer.get().getUser(), notNullValue());
        assertThrows(LazyInitializationException.class, () -> {
            customer.get().getConstructions().get(0);
        });
    }

    @Test
    public void findByCuit_NonExistentCuit_Fails() {

        Optional<Customer> customer = customerRepository.findByCuit("9999");

        assertTrue(customer.isEmpty());
    }

    @Test
    public void findByBusinessName_CustomerByBusinessName_CustomerRetrievedWithUser() {

        Optional<Customer> customer = customerRepository.findByBusinessName("customer1");

        assertTrue(customer.isPresent());
        assertThat(customer.get().getId(), is(equalTo(1)));
        assertThat(customer.get().getUser(), notNullValue());
        assertThrows(LazyInitializationException.class, () -> {
            customer.get().getConstructions().get(0);
        });
    }

    @Test
    public void findByIdObra_CustomerByConstruction_CustomerRetrievedWithConstructionWithGivenId() {

        Optional<Customer> customer = customerRepository.findByConstructionId(3);

        assertTrue(customer.isPresent());
        assertThat(customer.get().getId(), is(equalTo(1)));
    }
}