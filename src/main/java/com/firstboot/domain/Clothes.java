package com.firstboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Clothes {
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getCup() {
        return cup;
    }

    public String getColor() {
        return color;
    }

    private String cup;

    public Clothes() {
    }

    private String color;



}
