package com.durandsuppicich.danmsusuarios.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.TipoObra;
import com.durandsuppicich.danmsusuarios.domain.TipoUsuario;
import com.durandsuppicich.danmsusuarios.domain.Usuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
@Profile("testing")
public class ClienteJpaRepositoryTest {

    @Autowired
    ClienteJpaRepository clienteRepository;

    @Test
    @Sql({"/datos_test.sql"})
    public void save_ClienteOk_ClienteCreado () {
        TipoUsuario tipoUsuario = new TipoUsuario("Cliente");
        tipoUsuario.setId(1);
        Usuario usuario = new Usuario("usuario5", "clave5", tipoUsuario);
        TipoObra tipoObra = new TipoObra("Casa");
        tipoObra.setId(1);
        Obra obra = new Obra("descripcion3", Float.valueOf("3.0"), Float.valueOf("3.0"), "direccion3", 30, tipoObra);
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(obra);
        Cliente cliente = new Cliente("cliente3", "3333", "cliente3@emai.com", 3000.0, false, null, usuario, obras);

        clienteRepository.save(cliente);
        assertEquals(clienteRepository.count(), 3);
    }
    
}
