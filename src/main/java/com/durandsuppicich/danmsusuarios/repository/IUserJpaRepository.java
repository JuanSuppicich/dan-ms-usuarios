package com.durandsuppicich.danmsusuarios.repository;

import com.durandsuppicich.danmsusuarios.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserJpaRepository
        extends JpaRepository<User, Integer> {
}
