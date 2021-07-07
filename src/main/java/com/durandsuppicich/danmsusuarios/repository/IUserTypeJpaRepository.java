package com.durandsuppicich.danmsusuarios.repository;

import com.durandsuppicich.danmsusuarios.domain.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserTypeJpaRepository extends JpaRepository<UserType, Integer> {
}
