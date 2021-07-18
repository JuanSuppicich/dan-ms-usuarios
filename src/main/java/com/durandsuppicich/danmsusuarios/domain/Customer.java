package com.durandsuppicich.danmsusuarios.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
@Table(name = "customer", schema = "ms_users")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "business_name", nullable = false, length = 32)
    private String businessName;

    @Column(nullable = false, unique = true, length = 11)
    private String cuit;

    @Column(nullable = false)
    private String email;

    @Column(name = "max_current_account", scale = 3, precision = 7)
    private Double maxCurrentAccount;

    @Column(name = "allowed_online", nullable = false, columnDefinition = "boolean default false")
    private Boolean allowedOnline;

    @Column(name = "post_date" , nullable = false)
    private Instant postDate;

    @Column(name = "put_date")
    private Instant putDate;

    @Column(name = "delete_date")
    private Instant deleteDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
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

    public Instant getPostDate() {
        return postDate;
    }

    public void setPostDate(Instant postDate) {
        this.postDate = postDate.truncatedTo(ChronoUnit.SECONDS);
    }

    public Instant getPutDate() {
        return putDate;
    }

    public void setPutDate(Instant putDate) {
        this.putDate = putDate.truncatedTo(ChronoUnit.SECONDS);
    }

    public Instant getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Instant deleteDate) {
        this.deleteDate = deleteDate.truncatedTo(ChronoUnit.SECONDS);
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

    //This method help to synchronize the bidirectional relationship
    public void addConstruction(Construction construction) {
        if (this.constructions == null) {
            this.constructions = new ArrayList<>();
        }
        constructions.add(construction);
        construction.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer [cuit=" + cuit + ", deleteDate=" + deleteDate + ", allowedOnline=" + allowedOnline + ", id="
                + id + ", email=" + email + ", maxCurrentAccount=" + maxCurrentAccount + ", constructions=" + constructions
                + ", businessName=" + businessName + ", user=" + user + "]";
    }

}
