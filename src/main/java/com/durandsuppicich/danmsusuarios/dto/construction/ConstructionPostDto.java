package com.durandsuppicich.danmsusuarios.dto.construction;

import com.durandsuppicich.danmsusuarios.dto.OnConstructionPost;
import com.durandsuppicich.danmsusuarios.dto.OnCustomerPost;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

public class ConstructionPostDto {

    @NotBlank
    @Length(max = 128)
    private String description;

    @NotNull
    @Range(min = -90, max = 90)
    private Float latitude;

    @NotNull
    @Range(min = -180, max = 180)
    private Float longitude;

    @NotBlank
    @Length(max = 32)
    private String address;

    private Integer area;

    @NotNull
    @Range(min = 0, max = Integer.MAX_VALUE)
    private Integer constructionTypeId;

    @Null(groups = OnCustomerPost.class)
    @NotNull(groups = OnConstructionPost.class)
    @Positive(groups = OnConstructionPost.class)
    private Integer customerId;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getConstructionTypeId() {
        return constructionTypeId;
    }

    public void setConstructionTypeId(Integer constructionTypeId) {
        this.constructionTypeId = constructionTypeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public boolean validCustomerId() {
        return (this.customerId != null && this.customerId > 0);
    }
}
