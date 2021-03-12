package com.jh22.airbnb.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "propertyrenters")
@IdClass(PropertyRentersId.class)
public class PropertyRenters implements Serializable {

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "properties",
    allowSetters = true)
    private User renter;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "propertyid")
    @JsonIgnoreProperties(value = "renters",
    allowSetters = true)
    private Property property;

    public PropertyRenters() {
    }

    public PropertyRenters(@NotNull User renter,
                           @NotNull Property property) {
        this.renter = renter;
        this.property = property;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renters) {
        this.renter = renters;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property properties) {
        this.property = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PropertyRenters that = (PropertyRenters) o;

        return ((renter == null) ? 0 : renter.getUserid()) == ((that.renter == null) ? 0 :
                that.renter.getUserid()) &&
                ((property == null) ? 0 : property.getPropertyid()) == ((that.property == null) ? 0 :
                        that.property.getPropertyid());
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
