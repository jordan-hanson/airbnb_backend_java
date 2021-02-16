package com.jh22.airbnb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cards")
public class CardInfo extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardid;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer number;

    @NotNull
    private String type;

    @NotNull
    private Integer expdate;

    @NotNull
    private Integer securitycode;

    public CardInfo() {
    }

    public CardInfo(@NotNull String name,
                    @NotNull Integer number,
                    @NotNull String type,
                    @NotNull Integer expdate,
                    @NotNull Integer securitycode)
    {
        this.name = name;
        this.number = number;
        this.type = type;
        this.expdate = expdate;
        this.securitycode = securitycode;
    }

    public long getCardid() {
        return cardid;
    }

    public void setCardid(long cardid) {
        this.cardid = cardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExpdate() {
        return expdate;
    }

    public void setExpdate(Integer expdate) {
        this.expdate = expdate;
    }

    public Integer getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(Integer securitycode) {
        this.securitycode = securitycode;
    }
}
