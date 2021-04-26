package com.durandsuppicich.danmsusuarios;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.Usuario;

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
    
    
    @Test
    public void crear_ClienteSinObras_BadRequest() {
        String url = "http://localhost:" + puerto + "/api/cliente";
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        usuario.setClave("test");
        cliente.setUsuario(usuario);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate
            .exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void crear_ClienteSinUsuario_BadRequest() {
        String url = "http://localhost:" + puerto + "/api/cliente";
        Cliente cliente = new Cliente();
        Obra obra = new Obra();
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(obra);
        cliente.setObras(obras);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate
            .exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void crear_ClienteOk_Ok() {
        String url = "http://localhost:" + puerto + "/api/cliente";
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        usuario.setClave("test");
        Obra obra = new Obra();
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(obra);
        cliente.setObras(obras);
        cliente.setUsuario(usuario);

        HttpEntity<Cliente> request = new HttpEntity<Cliente>(cliente);
        ResponseEntity<Cliente> response = testRestTemplate
            .exchange(url, HttpMethod.POST, request, Cliente.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    



}
