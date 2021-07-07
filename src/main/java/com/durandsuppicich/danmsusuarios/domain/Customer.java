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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CLIENTE", schema = "MS_USUARIOS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer id;

    @Column(name = "RAZON_SOCIAL", nullable = false, length = 32)
    private String businessName;

    @Column(nullable = false, unique = true, length = 11)
    private String cuit;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MAX_CUENTA_CORRIENTE", scale = 3, precision = 7)
    private Double maxCurrentAccount;

    @Column(name = "HABILITADO_ONLINE", nullable = false, columnDefinition = "boolean default false")
    private Boolean allowedOnline;

    @Column(name = "FECHA_BAJA")
    private Instant deleteDate;

    //TODO add updateDate
    //TODO add postDate

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO")
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Construction> constructions;

    public Customer() {
    }

    public Customer(String businessName, String cuit, String email, Double maxCurrentAccount, Boolean allowedOnline,
                    Instant deleteDate, User user, List<Construction> constructions) {
        this.businessName = businessName;
        this.cuit = cuit;
        this.email = email;
        this.maxCurrentAccount = maxCurrentAccount;
        this.allowedOnline = allowedOnline;
        this.deleteDate = deleteDate;
        this.user = user;
        this.constructions = constructions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMaxCurrentAccount() {
        return maxCurrentAccount;
    }

    public void setMaxCurrentAccount(Double maxCurrentAccount) {
        this.maxCurrentAccount = maxCurrentAccount;
    }

    public Boolean getAllowedOnline() {
        return allowedOnline;
    }

    public void setAllowedOnline(Boolean allowedOnline) {
        this.allowedOnline = allowedOnline;
    }

    public Instant getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Instant deleteDate) {
        this.deleteDate = deleteDate;
    }

    public List<Construction> getConstructions() {
        return constructions;
    }

    public void setConstructions(List<Construction> constructions) {
        this.constructions = constructions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Metodo para sincronizar relacion bidireccional 
    public void addConstruction(Construction construction) {
        constructions.add(construction);
        construction.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer [cuit=" + cuit + ", deleteDate=" + deleteDate + ", allowedOnline=" + allowedOnline + ", id="
                + id + ", email=" + email + ", maxCurrentAccount=" + maxCurrentAccount + ", constructions=" + constructions
                + ", businessName=" + businessName + ", user=" + user + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
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
        Customer other = (Customer) obj;
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (businessName == null) {
            return other.businessName == null;
        } else return businessName.equals(other.businessName);
    }

}
