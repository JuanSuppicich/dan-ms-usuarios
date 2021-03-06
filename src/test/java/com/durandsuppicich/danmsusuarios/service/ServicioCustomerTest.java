package com.durandsuppicich.danmsusuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.repository.ICustomerJpaRepository;
import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.client.OrderDto;
import com.durandsuppicich.danmsusuarios.domain.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = DanMsUsuariosApplicationTests.class)
public class ServicioCustomerTest {

    @Autowired
    ICustomerService servicioCliente;

    @MockBean
    ICreditRiskService servicioRiesgo;

    @MockBean
    ICustomerJpaRepository clienteRepository;

    Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setBusinessName("test");
        customer.setCuit("11111111111");
        customer.setEmail("test@test.com");
        customer.setAllowedOnline(false);
        User user = new User("test", "test", null);
        customer.setUser(user);
        Construction construction = new Construction();
        List<Construction> constructions = new ArrayList<Construction>();
        constructions.add(construction);
        customer.setConstructions(constructions);
    }

    @Test
    public void crear_ReporteBCRAPositivo_HabilitadoOnlineTrue() {

        customer.setUser(null);

        when(servicioRiesgo.BcraPositiveReport(anyString())).thenReturn(true);
        when(clienteRepository.save(any(Customer.class))).thenReturn(customer);

        Customer resultado = servicioCliente.post(customer);
        assertTrue(resultado.getAllowedOnline());
        assertNotNull(resultado.getUser());
        verify(clienteRepository, times(1)).save(customer);
    }

    @Test
    public void crear_ReporteBCRANegativo_HabilitadoOnlineFalse() {

        customer.setUser(null);

        when(servicioRiesgo.BcraPositiveReport(anyString())).thenReturn(false);
        when(clienteRepository.save(any(Customer.class))).thenReturn(customer);

        Customer resultado = servicioCliente.post(customer);
        assertFalse(resultado.getAllowedOnline());
        assertNotNull(resultado.getUser());
        verify(clienteRepository, times(1)).save(customer);
    }

    @Test
    public void crear_CreacionDeUsuario_NombreUsuarioMail() {

        customer.setUser(null);

        when(servicioRiesgo.BcraPositiveReport(anyString())).thenReturn(true);
        when(clienteRepository.save(any(Customer.class))).thenReturn(customer);

        Customer resultado = servicioCliente.post(customer);

        assertTrue(resultado.getUser().getUsername().equals(resultado.getEmail()));
    }

    @Test
    public void eliminar_ClienteConPedidos_FechaBajaEstablecida() {

        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        orderDtos.add(new OrderDto());
        when(clienteRepository.existsById(anyInt())).thenReturn(true);
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        when(clienteRepository.save(any(Customer.class))).thenReturn(customer);

        servicioCliente.delete(1);
        assertNotNull(customer.getDeleteDate());
        verify(clienteRepository, times(1)).save(customer);
    }

    @Test
    public void eliminar_ClienteSinPedidos_ClienteEliminado() {

        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        when(clienteRepository.existsById(anyInt())).thenReturn(true);
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        doNothing().when(clienteRepository).deleteById(anyInt());

        servicioCliente.delete(1);
        assertNull(customer.getDeleteDate());
        verify(clienteRepository).deleteById(1);
    }

    @Test
    public void todos_ClientesConFechaBaja_ListaSinClientesConFechaBaja() {

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer2.setDeleteDate(Instant.now());
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);
        when(clienteRepository.findAll()).thenReturn(customers);

        List<Customer> resultado = servicioCliente.getAll();
        assertTrue(resultado.stream().allMatch(c -> c.getDeleteDate() == null));
        assertEquals(resultado.size(), 1);
    }

    @Test
    public void clientePorId_ClienteConFechaBaja_NoRecuperaCliente() {

        customer.setDeleteDate(Instant.now());
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(customer));

        Customer result = servicioCliente.getById(1);
        assertNull(result);
    }

    @Test
       public void clientePorId_ClienteSinFechaBaja_RecuperaCliente() {

        customer.setDeleteDate(null);
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(customer));

        Customer result = servicioCliente.getById(1);
        assertNull(result);
    }

}
