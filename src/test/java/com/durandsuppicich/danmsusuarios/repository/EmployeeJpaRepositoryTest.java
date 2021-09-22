package com.durandsuppicich.danmsusuarios.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Employee;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsUsuariosApplicationTests.class)
@Profile("testing")
public class EmployeeJpaRepositoryTest {
    
    @Autowired
    IEmployeeJpaRepository employeeRepository;

    @Test
    public void repositoryExists(){
        assertNotNull(employeeRepository);
    }

    @Sql("/test_data.sql")

    @Test
    public void findByName_EmployeeByName_EmployeeRetrieved() {

        Optional<Employee> employee = employeeRepository.findByName("employee1");

        assertTrue(employee.isPresent());
        assertThat(employee.get().getId(), is(equalTo(1)));
        assertThat(employee.get().getUser(), notNullValue());
        assertThat(employee.get().getUser().getId(), is(equalTo(3)));
    }
    
}
