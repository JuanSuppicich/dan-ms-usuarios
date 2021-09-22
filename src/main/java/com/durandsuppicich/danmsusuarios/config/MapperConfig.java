package com.durandsuppicich.danmsusuarios.config;

import com.durandsuppicich.danmsusuarios.mapper.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class MapperConfig {

    @Bean
    public ICustomerMapper customerMapper() {
        return new CustomerMapper(constructionMapper());
    }

    @Bean
    public IConstructionMapper constructionMapper() {
        return new ConstructionMapper();
    }

    @Bean
    public IEmployeeMapper employeeMapper() {
        return new EmployeeMapper();
    }
}
