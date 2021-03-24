package com.durandsuppicich.danmsusuarios.domain;

public class TipoUsuario {

    private Integer id;
    private String tipo;


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