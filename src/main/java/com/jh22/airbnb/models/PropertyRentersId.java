package com.jh22.airbnb.models;

import java.io.Serializable;
import java.util.Objects;

public class PropertyRentersId implements Serializable {
    private long user;
    private long property;

    public PropertyRentersId() {
    }

    public PropertyRentersId(long user, long property) {
        this.user = user;
        this.property = property;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
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
        PropertyRentersId that = (PropertyRentersId) o;
        return getUser() == that.getUser() && getProperty() == that.getProperty();
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
