package com.durandsuppicich.danmsusuarios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_USUARIO", schema = "MS_USUARIOS")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_USUARIO")
    private Integer id;

    @Column(name = "TIPO", nullable = false)
    private String description;

    public UserType() {
    }

    public UserType(String description) {
        this.description = description;
    }

    public UserType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserType [id=" + id + ", description=" + description + "]";
    }
}