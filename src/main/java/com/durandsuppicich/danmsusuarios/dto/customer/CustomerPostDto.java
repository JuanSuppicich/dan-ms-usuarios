package com.durandsuppicich.danmsusuarios.dto.customer;

import com.durandsuppicich.danmsusuarios.dto.construction.ConstructionPostDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class CustomerPostDto {

    @NotBlank
    @Size(max = 32)
    private String businessName;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cuit;

    @Email
    private String email;

    @NotNull
    @Min(0)
    @Digits(integer = 7, fraction = 3)
    private Double maxCurrentAccount;

    @NotEmpty
    private List<@Valid ConstructionPostDto> constructions;

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

    public List<ConstructionPostDto> getConstructions() {
        return constructions;
    }

    public void setConstructions(List<ConstructionPostDto> constructions) {
        this.constructions = constructions;
    }
}
