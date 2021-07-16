package com.durandsuppicich.danmsusuarios.dto.customer;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class CustomerPutDto {

    @NotNull
    @Range(min = 1, max = Integer.MAX_VALUE)
    private Integer id;

    @NotBlank
    @Length(max = 32)
    private String businessName;

    @Email
    private String email;

    @NotNull
    @Min(0)
    @Digits(integer = 7, fraction = 3)
    private Double maxCurrentAccount;

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
}
