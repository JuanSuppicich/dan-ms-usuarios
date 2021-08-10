package com.durandsuppicich.danmsusuarios.dto.construction;

public class ConstructionDto {

    private Integer id;

    private String description;

    private Float latitude;

    private Float longitude;

    private String address;

    private Integer area;

    private Integer constructionTypeId;

    private String customerBusinessName;

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

    public String getCustomerBusinessName() {
        return customerBusinessName;
    }

    public void setCustomerBusinessName(String customerBusinessName) {
        this.customerBusinessName = customerBusinessName;
    }

    public Integer getConstructionTypeId() {
        return constructionTypeId;
    }

    public void setConstructionTypeId(Integer constructionTypeId) {
        this.constructionTypeId = constructionTypeId;
    }
}
