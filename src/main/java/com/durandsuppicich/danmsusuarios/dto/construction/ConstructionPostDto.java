package com.durandsuppicich.danmsusuarios.dto.construction;

public class ConstructionPostDto {

    private String description;

    private Float latitude;

    private Float longitude;

    private String address;

    private Integer area;

    private Integer constructionTypeId;

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
