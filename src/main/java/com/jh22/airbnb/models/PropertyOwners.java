package com.jh22.airbnb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PropertyOwners extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "properties",
    allowSetters = true)
    private User owner;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "propertyid")
    @JsonIgnoreProperties(value = "users",
    allowSetters = true)
    private Property properties;

    public PropertyOwners() {
    }

    public PropertyOwners(@NotNull User owner, @NotNull Property properties) {
        this.owner = owner;
        this.properties = properties;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

        PropertyOwners that = (PropertyOwners) o;

        return ((owner == null) ? 0 : owner.getUserid()) == ((that.owner == null) ? 0 : that.owner.getUserid()) &&
                ((properties == null) ? 0 : properties.getPropertyid()) == ((that.properties == null) ? 0 : that.properties.getPropertyid());
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
