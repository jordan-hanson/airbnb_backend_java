package com.jh22.airbnb.models;


import java.io.Serializable;

public class PropertyOwnersId implements Serializable {
    private long owner;
    private long property;

    public PropertyOwnersId() {
    }

    public PropertyOwnersId(long owner, long property) {
        this.owner = owner;
        this.property = property;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public long getProperty() {
        return property;
    }

    public void setProperty(long property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PropertyOwnersId that = (PropertyOwnersId) o;

        return getOwner() == that.getOwner() && getProperty() == that.getProperty();
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
