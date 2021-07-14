package com.durandsuppicich.danmsusuarios.dto.construction;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConstructionPutDto {

    @NotNull
    @Range(min = 1, max = Integer.MAX_VALUE)
    private Integer id;

    @NotEmpty
    @NotBlank
    @Length(max = 128)
    private String description;

    @NotNull
    @Range(min = -90, max = 90)
    private Float latitude;

    @NotNull
    @Range(min = -180, max = 180)
    private Float longitude;

    @NotEmpty
    @NotBlank
    @Length(max = 32)
    private String address;

    private Integer area;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

}
