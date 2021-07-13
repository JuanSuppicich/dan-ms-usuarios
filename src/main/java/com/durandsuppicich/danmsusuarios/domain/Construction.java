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
import com.sun.istack.Nullable;

import java.time.Instant;

@Entity
@Table(name = "order", schema = "ms_users")
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(length = 128)
    private String description;

    @Column(nullable = false)
    private Float latitude;

    @Column(nullable = false)
    private Float longitude;

    @Column(nullable = false, length = 32)
    private String address;

    private Integer area;

    @Column(name = "post_date" ,nullable = false)
    private Instant postDate;

    @Column(name = "put_date")
    private Instant putDate;

    @Column(name = "delete_date")
    private Instant deleteDate;

    @OneToOne
    @JoinColumn(name = "oder_type_id")
    private ConstructionType constructionType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    public Construction() {
    }

    public Construction(String description, Float latitude, Float longitude, String address, Integer area,
                        ConstructionType constructionType) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
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

    public Instant getPostDate() {
        return postDate;
    }

    public void setPostDate(Instant postDate) {
        this.postDate = postDate;
    }

    public Instant getPutDate() {
        return putDate;
    }

    public void setPutDate(Instant putDate) {
        this.putDate = putDate;
    }

    public Instant getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Instant deleteDate) {
        this.deleteDate = deleteDate;
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
        return "Construction [description=" + description + ", direction=" + address + ", id=" + id + ", latitude=" + latitude
                + ", longitude=" + longitude + ", area=" + area + ", constructionType=" + constructionType + "]";
    }
}
