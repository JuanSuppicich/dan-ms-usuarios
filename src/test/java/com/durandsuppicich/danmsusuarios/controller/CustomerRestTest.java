package com.durandsuppicich.danmsusuarios.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.ConstructionType;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest (classes = DanMsUsuariosApplicationTests.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerRestTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    String port;

    Customer customer;

    String url;

    @BeforeEach
    public void setUp() {

        url = "http://localhost:" + port + "/api/customer";

        customer = new Customer();
        customer.setBusinessName("test");
        customer.setCuit("11111111111");
        customer.setEmail("test@test.com");
        customer.setAllowedOnline(false);
        customer.setMaxCurrentAccount(1000.00);

        Construction construction = new Construction();
        construction.setDescription("test");
        construction.setLatitude(1F);
        construction.setLongitude(1F);
        construction.setArea(1);
        construction.setAddress("test");
        
        ConstructionType constructionType = new ConstructionType(1, "Reforma"); //TODO change this
        construction.setConstructionType(constructionType);

        List<Construction> constructions = new ArrayList<>();
        constructions.add(construction);

        User user = new User("test", "test", null);

        customer.setUser(user);
        customer.setConstructions(constructions);
    }

    @Test
    @Sql({"/test_data.sql"})
    public void create_CustomerOk_Ok() {

        customer.setUser(null);

        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<Customer> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Customer.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void post_CustomerWithoutConstruction_BadRequest() {

        customer.setConstructions(new ArrayList<>());

        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<Customer> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Customer.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void post_CustomerWithConstructionWithoutConstructionType_BadRequest() {

        customer.getConstructions().get(0).setConstructionType(null);

        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<Customer> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Customer.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void post_RepeatedCustomer_Conflict() {

        Customer customer2 = new Customer();
        customer2.setBusinessName("test");
        customer2.setCuit("777777777");
        customer2.setEmail("test7@test.com");
        customer2.setAllowedOnline(false);
        customer2.setMaxCurrentAccount(1000.00);

        Construction construction = new Construction();
        construction.setDescription("test");
        construction.setLatitude(1F);
        construction.setLongitude(1F);
        construction.setArea(1);
        construction.setAddress("test");
        
        ConstructionType constructionType = new ConstructionType(1, "Reforma"); //TODO change this
        construction.setConstructionType(constructionType);

        List<Construction> constructions = new ArrayList<Construction>();
        constructions.add(construction);

        customer2.setConstructions(constructions);

        HttpEntity<Customer> request = new HttpEntity<Customer>(customer2);
        ResponseEntity<Customer> response1 = testRestTemplate.exchange(url, HttpMethod.POST, request, Customer.class);
        ResponseEntity<Customer> response2 = testRestTemplate.exchange(url, HttpMethod.POST, request, Customer.class);

        assertEquals(response1.getStatusCode(), HttpStatus.OK);
        assertEquals(response2.getStatusCode(), HttpStatus.CONFLICT);
    }
}
