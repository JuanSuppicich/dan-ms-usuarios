package com.durandsuppicich.danmsusuarios.domain;

public class Obra {
    
    private Integer id; 
    private String descripcion;
    private Float latitud;
    private Float longitud;
    private String direccion;
    private Integer superficie;
    private Cliente cliente;
    private TipoObra tipoObra;
    
    
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
