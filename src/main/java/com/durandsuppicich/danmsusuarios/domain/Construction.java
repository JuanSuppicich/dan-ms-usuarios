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
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "OBRA", schema = "MS_USUARIOS")
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBRA")
    private Integer id;

    @Column(name = "DESCRIPCION", length = 128)
    private String description;

    @Column(name = "LATITUD", nullable = false)
    private Float latitude;

    @Column(name = "LONGITUD", nullable = false)
    private Float longitude;

    @Column(name = "DIRECCION", nullable = false, length = 32)
    private String address;

    private Integer area; //TODO map?

    @OneToOne
    @JoinColumn(name = "ID_TIPO_OBRA")
    private ConstructionType constructionType;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    @JsonBackReference
    private Customer customer;

    public Construction() {
    }

    public Construction(String description, Float latitude, Float longitude, String adress, Integer area,
                        ConstructionType constructionType) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = adress;
        this.area = area;
        this.constructionType = constructionType;
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String direction) {
        this.address = direction;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    @Override
    public String toString() {
        return "Construction [descripcion=" + description + ", direction=" + address + ", id=" + id + ", latitude=" + latitude
                + ", longitude=" + longitude + ", area=" + area + ", constructionType=" + constructionType + "]";
    }
}
