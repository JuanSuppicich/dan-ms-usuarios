package com.durandsuppicich.danmsusuarios.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.DanMsUsuariosApplicationTests;
import com.durandsuppicich.danmsusuarios.domain.Construction;
import com.durandsuppicich.danmsusuarios.domain.Customer;
import com.durandsuppicich.danmsusuarios.domain.ConstructionType;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = DanMsUsuariosApplicationTests.class)
@Profile("testing")
public class ConstructionJpaRepositoryTest {

    @Autowired
    IConstructionJpaRepository obraRepository;

    @Test
    public void repositoryExists() {
        assertNotNull(obraRepository);
    }

    @Sql({"/test_data.sql"})

    @Test
    public void save_ConstructionOk_ConstructionCreated() {
        
        ConstructionType constructionType = new ConstructionType();
        constructionType.setId(1);
        Customer customer = new Customer();
        customer.setId(2);
        Construction construction =
                new Construction("desc3", 1F, 1F, "address3", 100, constructionType);
        construction.setCustomer(customer);

        Construction result = obraRepository.save(construction);
        
        assertThat(result.getId(), is(equalTo(4))); // too many asserts !
        assertThat(result.getCustomer(), notNullValue());
        assertThat(result.getCustomer().getId(), is(equalTo(2)));
        assertThat(result.getConstructionType(), notNullValue());
        assertThat(result.getConstructionType().getId(), is(equalTo(1)));
        assertThat(obraRepository.count(), is(equalTo(4L)));
    }

    @Test
    public void findById_ConstructionById_ConstructionRetrievedWithCustomerAndConstructionType() {

        Optional<Construction> construction = obraRepository.findById(1);
        
        assertTrue(construction.isPresent());
        assertThat(construction.get().getId(), is(equalTo(1)));
        assertThat(construction.get().getCustomer(), notNullValue());
        assertThat(construction.get().getCustomer().getId(), is(equalTo(1)));
        assertThat(construction.get().getConstructionType(), notNullValue());
        assertThat(construction.get().getConstructionType().getId(), is(equalTo(1)));
    }

    @Test
    public void findByCustomerIdOrConstructionId_BothParamsOk_OneConstructionRetrieved() {

        List<Construction> constructions =
                obraRepository.findByCustomerIdOrConstructionId(1, 3);

        assertThat(constructions.size(), is(equalTo(1)));
        assertThat(constructions.get(0).getId(), is(equalTo(3)));
        assertThat(constructions.get(0).getCustomer().getId(), is(equalTo(1)));
        assertThat(constructions.get(0).getConstructionType().getId(), is(equalTo(3)));
    }

    @Test
    public void findByCustomerIdOrConstructionId_ConstructionTypeIdNull_TwoConstructionRetrieved() {

        List<Construction> constructions =
                obraRepository.findByCustomerIdOrConstructionId(1, null);

        assertThat(constructions.size(), is(equalTo(2)));
        assertThat(constructions, everyItem(hasProperty("customer",
                hasProperty("id", is(equalTo(1))))));
    }

    @Test 
    public void findByCustomerIdOrConstructionId_CustomerIdNull_OneConstructionRetrieved() {

        List<Construction> constructions =
                obraRepository.findByCustomerIdOrConstructionId(null, 2);

        assertThat(constructions.size(), is(equalTo(1)));
        assertThat(constructions, everyItem(hasProperty("tipoObra",
                hasProperty("id", is(equalTo(2))))));
    }

    @Test 
    public void findByCustomerIdOrConstructionId_BothParamsNull_AllConstructionsRetrieved() {

        List<Construction> constructions =
                obraRepository.findByCustomerIdOrConstructionId(null, null);

        assertThat(constructions.size(), is(equalTo(4)));
    }
    
}
