package com.jh22.airbnb.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "properties")
public class Property extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long propertyid;

    @NotNull
    @Column
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private Integer zipcode;

    @NotNull
    private Double price;

    @NotNull
    private String pictures;

    @ManyToOne
    @JoinColumn(name = "userid",
    nullable = false)
    private User ownerUser;



    public Property() {
    }

    public Property(@NotNull String title,
                    @NotNull String description,
                    @NotNull String street,
                    @NotNull String city,
                    @NotNull String state,
                    @NotNull Integer zipcode,
                    @NotNull Double price,
                    @NotNull String pictures)
    {
        this.title = title;
        this.description = description;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.price = price;
        this.pictures = pictures;
    }

    public long getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(long propertyid) {
        this.propertyid = propertyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}