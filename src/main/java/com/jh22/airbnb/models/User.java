package com.jh22.airbnb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    @Email(message = "must be a valid email")
    private String primaryemail;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "owner",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "owner",
    allowSetters = true)
    private Set<PropertyOwners> ownerproperties = new HashSet<>();

    @OneToMany(mappedBy = "renter",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "renter",
    allowSetters = true)
    private Set<PropertyRenters> rentproperties = new HashSet<>();

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
    allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
    allowSetters = true)
    private Set<CardInfo> cards = new HashSet<>();

    public User() {
    }

    public User(@NotNull String firstname, @NotNull String lastname, @NotNull String username, @NotNull @Email(message = "must be a valid email") String primaryemail, @NotNull String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.primaryemail = primaryemail;
        this.password = password;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrimaryemail() {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail) {
        this.primaryemail = primaryemail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PropertyOwners> getOwnerproperties() {
        return ownerproperties;
    }

    public void setOwnerproperties(Set<PropertyOwners> ownerproperties) {
        this.ownerproperties = ownerproperties;
    }

    public Set<PropertyRenters> getRentproperties() {
        return rentproperties;
    }

    public void setRentproperties(Set<PropertyRenters> rentproperties) {
        this.rentproperties = rentproperties;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }

    public Set<CardInfo> getCards() {
        return cards;
    }

    public void setCards(Set<CardInfo> cards) {
        this.cards = cards;
    }
}
