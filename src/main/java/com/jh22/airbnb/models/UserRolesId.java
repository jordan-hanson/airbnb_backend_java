package com.jh22.airbnb.models;

import java.io.Serializable;
import java.util.Objects;

public class UserRolesId implements Serializable {
    private long user;
    private long role;

    public UserRolesId() {
    }

    public UserRolesId(long user, long role) {
        this.user = user;
        this.role = role;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesId that = (UserRolesId) o;
        return getUser() == that.getUser() && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return 22;
    }
}
