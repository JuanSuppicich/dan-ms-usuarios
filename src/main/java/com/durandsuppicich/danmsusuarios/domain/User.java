package com.durandsuppicich.danmsusuarios.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO", schema = "MS_USUARIOS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(name = "USUARIO", nullable = false, unique = true, updatable = false, length = 20)
    private String user;

    @Column(name = "CLAVE", nullable = false, length = 32)
    private String password;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_USUARIO")
    private UserType userType;

    public User() {
    }

    public User(String user, String password, UserType userType) {
        this.user = user;
        this.password = password;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User [password=" + password + ", id=" + id + ", userType=" + userType + ", user=" + user + "]";
    }
}
