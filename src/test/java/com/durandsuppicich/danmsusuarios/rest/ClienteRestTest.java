package com.durandsuppicich.danmsusuarios.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.TipoObra;
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

@SpringBootTest (classes = DanMsUsuariosApplicationTests.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
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
        obra.setDescripcion("test");
        obra.setLatitud(Float.valueOf("1.00"));
        obra.setLongitud(Float.valueOf("1.00"));
        obra.setSuperficie(1);
        obra.setDireccion("test");
        
        TipoObra tipoObra = new TipoObra(1, "Reforma");
        obra.setTipoObra(tipoObra);

        List<Obra> obras = new ArrayList<Obra>();
        obras.add(obra);

        Usuario usuario = new Usuario("test", "test", null);

        cliente.setUsuario(usuario);
        cliente.setObras(obras);
    }

    @Test
    public void crear_ClienteOk_Ok() {

        cliente.setUsuario(null);

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
    public void crear_ClienteConObraSinTipoObra_BadRequest() {

        cliente.getObras().get(0).setTipoObra(null);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);
    
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void crear_ClienteRepetido_Conflic() {

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response1 = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);
        ResponseEntity<Cliente> response2 = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);
        
        assertTrue(response1.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response2.getStatusCode().equals(HttpStatus.CONFLICT));
    }

    /*@Test
    public void crear_ClienteSinRazonSocial_BadRequest() {

        cliente.setRazonSocial(null);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate.exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }*/

}
