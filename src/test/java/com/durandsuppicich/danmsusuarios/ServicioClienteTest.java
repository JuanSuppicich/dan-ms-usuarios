package com.durandsuppicich.danmsusuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Pedido;
import com.durandsuppicich.danmsusuarios.repository.ClienteRepository;
import com.durandsuppicich.danmsusuarios.service.IServicioCliente;
import com.durandsuppicich.danmsusuarios.service.IServicioPedido;
import com.durandsuppicich.danmsusuarios.service.IServicioRiesgoCrediticio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = DanMsUsuariosApplication.class)
public class ServicioClienteTest {

    @Autowired
    IServicioCliente servicioCliente;
    @MockBean
    IServicioRiesgoCrediticio servicioRiesgo;
    @MockBean
    IServicioPedido servicioPedido;
    @MockBean
    ClienteRepository clienteRepository;
    Cliente cliente;
    

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setCuit("");
    }

    @Test
    public void crear_ReporteBCRAPositivo_HabilitadoOnlineTrue() {
        when(servicioRiesgo.resporteBCRAPositivo(anyString())).thenReturn(true);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = servicioCliente.crear(cliente);
        assertTrue(resultado.getHabilitadoOnline());
        //verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void crear_ReporteBCRANegativo_HabilitadoOnlineFalse() {
        when(servicioRiesgo.resporteBCRAPositivo(anyString())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = servicioCliente.crear(cliente);
        assertFalse(resultado.getHabilitadoOnline());
        //verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void eliminar_ClienteConPedidos_FechaBajaEstablecida() {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.add(new Pedido());
        when(clienteRepository.existsById(anyInt())).thenReturn(true);
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(cliente));
        when(servicioPedido.obtenerPedidos(any(Cliente.class))).thenReturn(pedidos);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        servicioCliente.eliminar(1);
        assertNotNull(cliente.getFechaBaja());
    }

    @Test
    public void eliminar_ClienteSinPedidos_ClienteEliminado() {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        when(clienteRepository.existsById(anyInt())).thenReturn(true);
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(cliente));
        when(servicioPedido.obtenerPedidos(any(Cliente.class))).thenReturn(pedidos);
        //doNothing().when(clienteRepository).deleteById(anyInt());

        servicioCliente.eliminar(1);
        assertNull(cliente.getFechaBaja());
        verify(clienteRepository).deleteById(1);
    }

    @Test
    public void todos_ClientesConFechaBaja_ListaSinClientesConFechaBaja() {
        Cliente cliente1 = new Cliente(); 
        Cliente cliente2 = new Cliente();
        cliente2.setFechaBaja(Instant.now());
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> resultado = servicioCliente.todos();
        assertTrue(resultado.stream().allMatch(c -> c.getFechaBaja() == null));
        assertEquals(resultado.size(), 1);
    }

    @Test
    public void clientePorId_ClienteConFechaBaja_NoRecuperaCliente() {
        Cliente cliente = new Cliente();
        cliente.setFechaBaja(Instant.now());
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = servicioCliente.clientePorId(1);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void clientePorId_ClienteSinFechaBaja_RecuperaCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = servicioCliente.clientePorId(1);
        assertTrue(resultado.isPresent());
    }
    
}
