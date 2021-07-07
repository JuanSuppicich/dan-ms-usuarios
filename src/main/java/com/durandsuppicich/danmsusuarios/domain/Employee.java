package com.durandsuppicich.danmsusuarios.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADO", schema = "MS_USUARIOS")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPLEADO")
    private Integer id;

    @Column(name = "NOMBRE", nullable = false, length = 32)
    private String name;

    @Column(name = "MAIL", nullable = false)
    private String email;

    //TODO add createDate
    //TODO add updateDate
    //TODO add postDate

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", email=" + email + ", name=" + name + ", user=" + user + "]";
    }

}
