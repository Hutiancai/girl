package com.firstboot.domain;

import javax.persistence.*;


@Entity
@Table(name="clothes")
public class Clothes {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String cup;

    @Column
    private String color;

    public Clothes() {
    }

    public Clothes(String cup, String color) {
        this.cup = cup;
        this.color = color;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public String getCup() {
        return cup;
    }

    public String getColor() {
        return color;
    }

}
