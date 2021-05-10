package com.durandsuppicich.danmsusuarios.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Cliente;
import com.durandsuppicich.danmsusuarios.domain.Obra;
import com.durandsuppicich.danmsusuarios.domain.TipoObra;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsUsuariosApplicationTests.class)
@Profile("testing")
public class ObraJpaRepositoryTest {

    @Autowired
    ObraJpaRepository obraRepository;

    @Test
    public void elRepositorioExiste() {
        assertNotNull(obraRepository);
    }

    @Sql({"/datos_test.sql"})

    @Test
    public void save_ObraOk_ObraCreada() {
        
        TipoObra tipoObra = new TipoObra();
        tipoObra.setId(1);
        Cliente cliente = new Cliente(); 
        cliente.setId(2);
        Obra obra = new Obra("desc3", 1F, 1F, "direc3", 100, tipoObra);
        obra.setCliente(cliente);

        Obra resultado = obraRepository.save(obra);
        
        assertThat(resultado.getId(), is(equalTo(4)));
        assertThat(resultado.getCliente(), notNullValue());
        assertThat(resultado.getCliente().getId(), is(equalTo(2)));
        assertThat(resultado.getTipoObra(), notNullValue());
        assertThat(resultado.getTipoObra().getId(), is(equalTo(1)));
        assertThat(obraRepository.count(), is(equalTo(4L)));
    }

    @Test
    public void findById_ObraPorId_ObraRecuperadaConClienteYTipoObra() {

        Optional<Obra> obra = obraRepository.findById(1);
        
        assertTrue(obra.isPresent());
        assertThat(obra.get().getId(), is(equalTo(1)));
        assertThat(obra.get().getCliente(), notNullValue());
        assertThat(obra.get().getCliente().getId(), is(equalTo(1)));
        assertThat(obra.get().getTipoObra(), notNullValue());
        assertThat(obra.get().getTipoObra().getId(), is(equalTo(1)));
    }

    @Test
    public void findByIdClienteOrIdTipoObra_AmbosParametrosOk_UnaObraRecuperada() {

        List<Obra> obras = obraRepository.findByIdClienteOrIdTipoObra(1, 3);

        assertThat(obras.size(), is(equalTo(1)));
        assertThat(obras.get(0).getId(), is(equalTo(3)));
        assertThat(obras.get(0).getCliente().getId(), is(equalTo(1)));
        assertThat(obras.get(0).getTipoObra().getId(), is(equalTo(3)));
    }

    @Test
    public void findByIdClienteOrIdTipoObra_IdTipoObraNull_DosObrasRecuperadas() {

        List<Obra> obras = obraRepository.findByIdClienteOrIdTipoObra(1, null);

        assertThat(obras.size(), is(equalTo(2)));
        assertThat(obras, everyItem(hasProperty("cliente", hasProperty("id", is(equalTo(1))))));
    }

    @Test 
    public void findByIdClienteOrIdTipoObra_IdClienteNull_UnaObraRecuperada() {

        List<Obra> obras = obraRepository.findByIdClienteOrIdTipoObra(null, 2);

        assertThat(obras.size(), is(equalTo(1)));
        assertThat(obras, everyItem(hasProperty("tipoObra", hasProperty("id", is(equalTo(2))))));
    }

    @Test 
    public void findByIdClienteOrIdTipoObra_AmbosParametrosNull_RecuperaTodas() {

        List<Obra> obras = obraRepository.findByIdClienteOrIdTipoObra(null, null);

        assertThat(obras.size(), is(equalTo(4)));
    }
    
}
