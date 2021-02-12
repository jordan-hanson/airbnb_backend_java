package com.jh22.airbnb.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PropertyRentersId implements Serializable {
    private long renter;
    private long property;

    public PropertyRentersId() {
    }

    public PropertyRentersId(long renter, long property) {
        this.renter = renter;
        this.property = property;
    }

    public long getRenter() {
        return renter;
    }

    public void setRenter(long renter) {
        this.renter = renter;
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

        return getRenter() == that.getRenter() && getProperty() == that.getProperty();
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
