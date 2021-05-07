package com.durandsuppicich.danmsusuarios.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.TipoObra;
import com.durandsuppicich.danmsusuarios.domain.TipoUsuario;
import com.durandsuppicich.danmsusuarios.domain.Usuario;

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
public class ClienteJpaRepositoryTest {

    @Autowired
    ClienteJpaRepository clienteRepository;

    @Test
    public void elRepositorioExiste() {
        assertNotNull(clienteRepository);
    }

    @Sql({"/datos_test.sql"})
    
    @Test
    public void save_ClienteOk_ClienteCreado() {

        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(1);
        Usuario usuario = new Usuario("usuario5", "clave5", tipoUsuario);
        TipoObra tipoObra = new TipoObra();
        tipoObra.setId(1);
        Obra obra = new Obra("descripcion3", Float.valueOf("3.0"), Float.valueOf("3.0"), "direccion3", 30, tipoObra);
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(obra);
        Cliente cliente = new Cliente("cliente3", "3333", "cliente3@emai.com", 3000.0, false, null, usuario, obras);

        Cliente resultado = clienteRepository.save(cliente);

        assertThat(clienteRepository.count(), is(equalTo(3L)));
        assertThat(resultado.getId(), is(equalTo(3)));
        assertThat(resultado.getUsuario(), notNullValue());
        assertThat(resultado.getUsuario().getId(), is(equalTo(5)));
    }

    @Test
    public void save_ClienteSinObras_ClienteCreado() {
        
        TipoUsuario tipoUsuario = new TipoUsuario("Cliente");
        tipoUsuario.setId(1);
        Usuario usuario = new Usuario("usuario6", "clave6", tipoUsuario);
        TipoObra tipoObra = new TipoObra("Casa");
        tipoObra.setId(1);
        Cliente cliente = new Cliente("cliente4", "4444", "cliente4@emai.com", 4000.0, false, null, usuario, null);

        clienteRepository.save(cliente);

        assertThat(clienteRepository.count(), is(equalTo(4L)));
    }

    @Test
    public void findById_ClientePorId_ClienteRecuperadoConUsuario() {

        Optional<Cliente> cliente = clienteRepository.findById(2);

        assertTrue(cliente.isPresent());
        assertThat(cliente.get().getId(), is(equalTo(1)));
        assertThat(cliente.get().getUsuario(), notNullValue());
        assertThrows(LazyInitializationException.class, () -> {
            cliente.get().getObras().get(0);
        });
    }

    @Test
    public void findClienteConObras_ClientePorId_ClienteRecuperadoConUsuarioYObras() {
        
        Cliente cliente = clienteRepository.findClienteConObras(1).get(0);
        
        assertThat(cliente, notNullValue());
        assertThat(cliente.getId(), is(equalTo(1)));
        assertThat(cliente.getUsuario(), notNullValue());
        assertThat(cliente.getObras(), hasSize(2));
    }

    @Test
    public void findByCuit_ClientePorCuit_ClienteRecuperadoConUsuario() {

        Optional<Cliente> cliente = clienteRepository.findByCuit("2222");

        assertTrue(cliente.isPresent());
        assertThat(cliente.get().getId(), is(equalTo(2)));
        assertThat(cliente.get().getUsuario(), notNullValue());
        assertThrows(LazyInitializationException.class, () -> {
            cliente.get().getObras().get(0);
        });
    }

    @Test
    public void findByCuit_ClientePorCuitInexistente_Falla() {

        Optional<Cliente> cliente = clienteRepository.findByCuit("9999");

        assertTrue(cliente.isEmpty());
    }

    @Test
    public void findByRazonSocial_ClientePorRazonSocial_ClienteRecuperadoConUsuario() {

        Optional<Cliente> cliente = clienteRepository.findByRazonSocial("cliente1");

        assertTrue(cliente.isPresent());
        assertThat(cliente.get().getId(), is(equalTo(1)));
        assertThat(cliente.get().getUsuario(), notNullValue());
        assertThrows(LazyInitializationException.class, () -> {
            cliente.get().getObras().get(0);
        });
    }
}