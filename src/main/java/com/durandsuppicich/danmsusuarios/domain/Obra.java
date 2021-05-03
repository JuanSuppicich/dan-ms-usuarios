package com.durandsuppicich.danmsusuarios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OBRA", schema = "MS_USUARIOS")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBRA")
    private Integer id;

    @Column(length = 128)
    private String descripcion;

    @Column(nullable = false)
    private Float latitud;

    @Column(nullable = false)
    private Float longitud;

    @Column(nullable = false, length = 32)
    private String direccion;

    private Integer superficie;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_OBRA")
    private TipoObra tipoObra;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    @JsonIgnore
    private Cliente cliente;

    public Obra() {
    }

    public Obra(String descripcion, Float latitud, Float longitud, String direccion, Integer superficie,
            TipoObra tipoObra) {
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.superficie = superficie;
        this.tipoObra = tipoObra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoObra getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(TipoObra tipoObra) {
        this.tipoObra = tipoObra;
    }

    @Override
    public String toString() {
        return "Obra [descripcion=" + descripcion + ", direccion=" + direccion + ", id=" + id + ", latitud=" + latitud
                + ", longitud=" + longitud + ", superficie=" + superficie + ", tipoObra=" + tipoObra + "]";
    }
}
