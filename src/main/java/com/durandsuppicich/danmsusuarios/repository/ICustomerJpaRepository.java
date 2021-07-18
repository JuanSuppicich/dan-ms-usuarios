package com.durandsuppicich.danmsusuarios.repository;

import java.util.List;
import java.util.Optional;

import com.durandsuppicich.danmsusuarios.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerJpaRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c " +
            "FROM Customer c " +
            "WHERE c.deleteDate IS NULL" )
    List<Customer> getAll();

    Optional<Customer> findByIdAndDeleteDateIsNull(Integer id);

    Optional<Customer> findByCuit(String cuit);

    Optional<Customer> findByCuitAndDeleteDateIsNull(String cuit);

    Optional<Customer> findByBusinessName(String businessName);

    Optional<Customer> findByBusinessNameAndDeleteDateIsNull(String businessName);

    @Query("SELECT c " +
            "FROM Customer c " +
            "JOIN c.constructions o " +
            "WHERE o.id = :constructionId" +
            "   AND c.deleteDate IS NULL"
    )
    Optional<Customer> findByConstructionId(@Param("constructionId") Integer constructionId);

}
