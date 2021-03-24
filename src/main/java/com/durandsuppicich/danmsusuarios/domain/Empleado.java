package com.durandsuppicich.danmsusuarios.domain;

public class Empleado {

    private Integer id;
    private String mail;
    private Usuario usuario;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    @Override
    public String toString() {
        return "Empleado [id=" + id + ", mail=" + mail + "]";
    }
    
}
