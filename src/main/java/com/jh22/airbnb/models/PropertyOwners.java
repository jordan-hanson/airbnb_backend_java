package com.jh22.airbnb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "propertyowners")
@IdClass(PropertyOwnersId.class)
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
    @JsonIgnoreProperties(value = "owner",
    allowSetters = true)
    private Property property;

    public PropertyOwners() {
    }

    public PropertyOwners(@NotNull User owner, @NotNull Property property) {
        this.owner = owner;
        this.property = property;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PropertyOwners that = (PropertyOwners) o;

        return ((owner == null) ? 0 : owner.getUserid()) == ((that.owner == null) ? 0 : that.owner.getUserid()) &&
                ((property == null) ? 0 : property.getPropertyid()) == ((that.property == null) ? 0 : that.property.getPropertyid());
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
