package com.jh22.airbnb.models;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
@IdClass(UserRolesId.class)
public class UserRoles {
}
