package com.jh22.airbnb.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "propertyrenters")
@IdClass(PropertyRentersId.class)
public class PropertyRenters extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "properties",
    allowSetters = true)
    private User renters;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "propertyid")
    @JsonIgnoreProperties(value = "users",
    allowSetters = true)
    private Property properties;

    public PropertyRenters() {
    }

    public PropertyRenters(@NotNull User renters,
                           @NotNull Property properties) {
        this.renters = renters;
        this.properties = properties;
    }

    public User getRenters() {
        return renters;
    }

    public void setRenters(User renters) {
        this.renters = renters;
    }

    public Property getProperties() {
        return properties;
    }

    public void setProperties(Property properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PropertyRenters that = (PropertyRenters) o;

        return ((renters == null) ? 0 : renters.getUserid()) == ((that.renters == null) ? 0 :
                that.renters.getUserid()) &&
                ((properties == null) ? 0 : properties.getPropertyid()) == ((that.properties == null) ? 0 :
                        that.properties.getPropertyid());
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
