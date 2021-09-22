package com.durandsuppicich.danmsusuarios.repository;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Construction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface IConstructionJpaRepository extends JpaRepository<Construction, Integer> {

    @Query("SELECT DISTINCT o " +
            "FROM Construction o " +
            "WHERE (:customerId is null or o.customer.id = :customerId) " +
            "   and (:constructionTypeId is null or o.constructionType.id = :constructionTypeId)"
    )
    List<Construction> findByCustomerIdOrConstructionId(
            @Param("customerId") Integer customerId,
            @Param("constructionTypeId") Integer constructionTypeId
    );

    @Query("SELECT DISTINCT o " +
            "FROM Construction o " +
            "JOIN o.customer c " +
            "WHERE c.cuit = :cuit"
    )
    List<Construction> findByCuit(@Param("cuit") String cuit);

}
