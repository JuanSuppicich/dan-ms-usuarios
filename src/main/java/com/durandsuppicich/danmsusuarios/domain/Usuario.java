package com.durandsuppicich.danmsusuarios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO", schema = "MS_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(nullable = false, unique = true, updatable = false, length = 20)
    private String usuario;

    @Column(nullable = false, length = 32)
    private String clave;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_USUARIO")
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String usuario, String clave, TipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario [clave=" + clave + ", id=" + id + ", usuario=" + usuario + "]";
    }

}
