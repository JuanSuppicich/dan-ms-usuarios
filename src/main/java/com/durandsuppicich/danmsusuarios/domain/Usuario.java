package com.durandsuppicich.danmsusuarios.domain;

public class Usuario {
    
    private Integer id;
    private String usuario;
    private String clave;
    private TipoUsuario tipoUsuario;

    
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
