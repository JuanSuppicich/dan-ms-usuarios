package com.durandsuppicich.danmsusuarios.domain;

import java.util.List;

public class Cliente {
    
    private Integer id; 
    private String razonSocial; 
    private String cuit; 
    private String mail;
    private Double maxCuentaCorriente;
    private Boolean habilitadoOnline;
    private List<Obra> obras;
    private Usuario usuario;
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Double getMaxCuentaCorriente() {
        return maxCuentaCorriente;
    }
    public void setMaxCuentaCorriente(Double maxCuentaCorriente) {
        this.maxCuentaCorriente = maxCuentaCorriente;
    }
    public Boolean getHabilitadoOnline() {
        return habilitadoOnline;
    }
    public void setHabilitadoOnline(Boolean habilitadoOnline) {
        this.habilitadoOnline = habilitadoOnline;
    }
    public List<Obra> getObras() {
        return obras;
    }
    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    @Override
    public String toString() {
        return "Cliente [cuit=" + cuit + ", habilitadoOnline=" + habilitadoOnline + ", id=" + id + ", mail=" + mail
                + ", maxCuentaCorriente=" + maxCuentaCorriente + ", razonSocial=" + razonSocial + "]";
    } 

}
