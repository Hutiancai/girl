package com.firstboot.domain;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * @author hcs
 */
public class Filter implements Serializable {
    private static final long serialVersionUID = 8614082590271201016L;

    public enum Operator{
        and, or, eq, ne, gt, lt, ge, le, in, isNotNull, isNull, like, between;

    }

    private String property;

    private Filter.Operator operator;

    private Object value;

    private Object andOrFlag;

    public Filter(String property, Filter.Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }


    /*
    * 等于
    * */
    public static Filter eq(String property , Object value){
        return new Filter(property, Operator.eq, value);
    }
    /*
    * 不等于
    * */
    public static Filter ne(String property , Object value){
        return new Filter(property, Operator.ne, value);
    }
    /*
    * 大于
    * */
    public static Filter gt(String property , Object value){
        return new Filter(property, Operator.gt, value);
    }
    /*
    * 小于
    * */
    public static Filter lt(String property , Object value){
        return new Filter(property, Operator.lt, value);
    }
    /*
    * 大于等于
    * */
    public static Filter ge(String property , Object value){
        return new Filter(property, Operator.ge, value);
    }
    /*
    * 小于等于
    * */
    public static Filter le(String property , Object value){
        return new Filter(property, Operator.le, value);
    }
    /*
    * 包含
    * */
    public static Filter in(String property , Object value){
        return new Filter(property, Operator.in, value);
    }
    /*
    * 为空
    * */
    public static Filter isNull(String property){
        return new Filter(property, Operator.isNull, null);
    }
    /*
    * 不为空
    * */
    public static Filter isNotNull(String property){
        return new Filter(property, Operator.isNotNull,null);
    }
    /*
    * 相似
    * */
    public static Filter like(String property, Object value) {
        return new Filter(property, Operator.like, value);
    }
    /*
    * 两者之间
    * */
    public static Filter between(String property , Object value){
        return new Filter(property, Operator.between, value);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getAndOrFlag() {
        return andOrFlag;
    }

    public void setAndOrFlag(Object andOrFlag) {
        this.andOrFlag = andOrFlag;
    }

}
