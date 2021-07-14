package com.durandsuppicich.danmsusuarios.dto.employee;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeePutDto {

    @NotNull
    @Range(min = 1, max = Integer.MAX_VALUE)
    private Integer id;

    @NotEmpty
    @NotBlank
    @Length(max = 32)
    private String name;

    @Email
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
