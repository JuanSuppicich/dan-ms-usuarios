package com.durandsuppicich.danmsusuarios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_USUARIO", schema = "MS_USUARIOS")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_USUARIO")
    private Integer id; 

    @Column(nullable = false)
    private String tipo;

    public TipoUsuario() {
    }

    public TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public TipoUsuario(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TipoUsuario [id=" + id + ", tipo=" + tipo + "]";
    }

}