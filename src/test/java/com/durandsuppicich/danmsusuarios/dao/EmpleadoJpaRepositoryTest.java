package com.durandsuppicich.danmsusuarios.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Empleado;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsUsuariosApplicationTests.class)
@Profile("testing")
public class EmpleadoJpaRepositoryTest {
    
    @Autowired
    EmpleadoJpaRepository empleadoRepository;

    @Test
    public void elRepositorioExiste(){ 
        assertNotNull(empleadoRepository);
    }

    @Sql("/datos_test.sql")

    @Test
    public void findByNombre_EmpladoPorNombre_EmpleadoRecuperado() {

        Optional<Empleado> empleado = empleadoRepository.findByNombre("empleado1");

        assertTrue(empleado.isPresent());
        assertThat(empleado.get().getId(), is(equalTo(1)));
        assertThat(empleado.get().getUsuario(), notNullValue());
        assertThat(empleado.get().getUsuario().getId(), is(equalTo(3)));
    }
    
}
