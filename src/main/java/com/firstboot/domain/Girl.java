package com.firstboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by acer on 2018/1/13.
 */
@Entity
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "must money ")
    private Double money;

    private String cup;

    @Min(value = 18,message = "low 18")
    private Integer age;

    public Girl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", money=" + money +
                ", cup='" + cup + '\'' +
                ", age=" + age +
                '}';
    }
}
