package com.durandsuppicich.danmsusuarios.repository;

import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerJpaRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCuit(String cuit);

    Optional<Customer> findByBusinessName(String BusinessName);

    @Query("SELECT c " +
            "FROM Customer c " +
            "JOIN c.constructions o " +
            "WHERE o.id = :constructionId"
    )
    Optional<Customer> findByConstructionId(@Param("constructionId") Integer constructionId);

}
