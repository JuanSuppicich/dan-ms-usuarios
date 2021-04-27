package com.durandsuppicich.danmsusuarios.domain;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer id;
    
    @Column(nullable = false, length = 32)
    private String razonSocial;
    
    @Column(nullable = false, unique = true, length = 11)
    private String cuit;
    
    @Column(nullable = false)
    private String mail;

    @Column(scale = 3, precision = 7)
    private Double maxCuentaCorriente;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean habilitadoOnline;

    private Instant fechaBaja;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Obra> obras;
    
    
    
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
    public Instant getFechaBaja() {
        return fechaBaja;
    }
    public void setFechaBaja(Instant fechaBaja) {
        this.fechaBaja = fechaBaja;
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
        return "Cliente [cuit=" + cuit + ", fechaBaja=" + fechaBaja + ", habilitadoOnline=" + habilitadoOnline + ", id="
                + id + ", mail=" + mail + ", maxCuentaCorriente=" + maxCuentaCorriente + ", razonSocial=" + razonSocial
                + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((mail == null) ? 0 : mail.hashCode());
        result = prime * result + ((razonSocial == null) ? 0 : razonSocial.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (cuit == null) {
            if (other.cuit != null)
                return false;
        } else if (!cuit.equals(other.cuit))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (mail == null) {
            if (other.mail != null)
                return false;
        } else if (!mail.equals(other.mail))
            return false;
        if (razonSocial == null) {
            if (other.razonSocial != null)
                return false;
        } else if (!razonSocial.equals(other.razonSocial))
            return false;
        return true;
    } 
    

}
