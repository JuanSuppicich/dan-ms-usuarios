package com.durandsuppicich.danmsusuarios.dao;

import java.util.List;

import com.durandsuppicich.danmsusuarios.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> { 

    @Query("SELECT c FROM Cliente c JOIN FETCH c.obras")
    List<Cliente> findClienteConObras(Integer id);
    
}
