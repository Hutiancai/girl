package com.firstboot.domain;

import java.io.Serializable;

public class Filter implements Serializable {

    private String property;

    private String operator;

    private Object value;

    private Object lastvalue;

    public Filter(String property, String operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public Filter(String property, String operator, Object value, Object lastvalue) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.lastvalue = lastvalue;
    }

    public Filter(String property){
        this.property = property;
    }
    /*
    * 等于
    * */
    public static Filter eq(String property , Object value){
        return new Filter(property,"eq",value);
    }
    /*
    * 不等于
    * */
    public static Filter ne(String property , Object value){
        return new Filter(property,"ne",value);
    }
    /*
    * 大于
    * */
    public static Filter gt(String property , Object value){
        return new Filter(property,"gt",value);
    }
    /*
    * 小于
    * */
    public static Filter lt(String property , Object value){
        return new Filter(property,"lt",value);
    }
    /*
    * 大于等于
    * */
    public static Filter ge(String property , Object value){
        return new Filter(property,"ge",value);
    }
    /*
    * 小于等于
    * */
    public static Filter le(String property , Object value){
        return new Filter(property,"le",value);
    }
    /*
    * 包含
    * */
    public static Filter in(String property , Object value){
        return new Filter(property,"in",value);
    }
    /*
    * 为空
    * */
    public static Filter isNull(String property){
        return new Filter(property,"isNull",null);
    }
    /*
    * 不为空
    * */
    public static Filter isNotNull(String property){
        return new Filter(property,"isNotNull",null);
    }
    /*
    * 相似
    * */
    public static Filter like(String property , Object value){
        return new Filter(property,"like",value);
    }
    /*
    * 两者之间
    * */
    public static Filter between(String property , Object value , Object lastvalue){
        return new Filter(property,"between", value, lastvalue);
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

    public Object getLastvalue() {
        return lastvalue;
    }

    public void setLastvalue(Object lastvalue) {
        this.lastvalue = lastvalue;
    }
}
