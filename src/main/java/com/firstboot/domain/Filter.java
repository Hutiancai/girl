package com.firstboot.domain;

import java.io.Serializable;

public class Filter implements Serializable {

    private String property;

    private String operator;

    private Object value;

    public Filter(String property, String operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
