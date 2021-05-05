package com.durandsuppicich.danmsusuarios.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.Usuario;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClienteRestTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    String puerto;

    Cliente cliente;

    String url;

    @BeforeEach
    public void setUp() {

        url = "http://localhost:" + puerto + "/api/cliente";

        cliente = new Cliente();
        cliente.setRazonSocial("test");
        cliente.setCuit("11111111111");
        cliente.setMail("test@test.com");
        cliente.setHabilitadoOnline(false);
        cliente.setMaxCuentaCorriente(1000.00);

        Obra obra = new Obra();
        obra.setLatitud(Float.valueOf("1.00"));
        obra.setLongitud(Float.valueOf("1.00"));
        obra.setDireccion("test");
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(obra);

        Usuario usuario = new Usuario("test", "test", null);

        cliente.setUsuario(usuario);
        cliente.setObras(obras);
    }

    @Test
    public void crear_ClienteOk_Ok() {

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void crear_ClienteSinObras_BadRequest() {

        cliente.setObras(new ArrayList<Obra>());

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void crear_ClienteSinUsuario_BadRequest() {

        cliente.setUsuario(null);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    /*@Test
    public void crear_ClienteRepetido_Conflic() {

        HttpEntity<Cliente> request1 = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response1 = testRestTemplate.exchange(url, HttpMethod.POST, request1, Cliente.class);
        
        HttpEntity<Cliente> request2 = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response2 = testRestTemplate.exchange(url, HttpMethod.POST, request2, Cliente.class);
        
        assertTrue(response1.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response2.getStatusCode().equals(HttpStatus.CONFLICT));
    }*/

    /*@Test
    public void crear_ClienteSinRazonSocial_BadRequest() {

        cliente.setRazonSocial(null);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }*/

}
