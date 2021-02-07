package com.jh22.airbnb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

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

    @OneToMany(mappedBy = "property",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Property> properties = new ArrayList<>();

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
    allowSetters = true)
    private Set<PropertyRenters> rentProperties = new HashSet<>();

    public User() {
    }

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull @Email(message = "must be a valid email") String primaryemail, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
