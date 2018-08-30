package com.firstboot.repository;

import com.firstboot.domain.Filter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Query<T> implements Specification<T> {

    private List<Filter> andList = new ArrayList<>();

    private List<Filter> orList = new ArrayList<>();

    public Query and(Filter filter){
        this.andList.add(filter);
        return this;
    }

    public Query and(Filter ...filters){
        for(Filter newfilters:filters){
            this.andList.add(newfilters);
        }
        return this;
    }

    public Query or(Filter filter){
        this.orList.add(filter);
        return this;
    }

    public Query or(Filter ...filters){
        for(Filter newfilters:filters){
            this.orList.add(newfilters);
        }
        return this;
    }

    private Predicate toAndPredicate(Root<T> root,CriteriaBuilder cb){
        Predicate restrictions = null;
        for(Filter filter:andList){
            if(filter == null){
                continue;
            }
            String property = filter.getProperty();
            Object value = filter.getValue();
            String operator = filter.getOperator();

            switch(operator){
                case "eq":
                    if(restrictions == null){
                        restrictions = cb.equal(root.get(property),value);
                    }
                    else{
                        restrictions  = cb.and(restrictions,cb.equal(root.get(property),value));
                    }
                    break;
                case "ne":
                    if(restrictions == null){
                        restrictions = cb.notEqual(root.get(property),value);
                    }
                    else{
                        restrictions = cb.and(restrictions,cb.notEqual(root.get(property),value));
                    }
                    break;
                case "gt":
                    if(restrictions == null){
                        restrictions = cb.gt(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.and(restrictions,cb.gt(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "lt":
                    if(restrictions == null){
                        restrictions = cb.lt(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.and(restrictions,cb.lt(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "ge":
                    if(restrictions == null){
                        restrictions = cb.ge(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.and(restrictions,cb.ge(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "le":
                    if(restrictions == null){
                        restrictions = cb.le(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.and(restrictions,cb.le(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "in":
                    if(restrictions == null){
                        restrictions = cb.in(root.get(property)).value(value);
                    }
                    else{
                        restrictions = cb.and(restrictions,cb.in(root.get(property)).value(value));
                    }
            }
        }
        return restrictions;
    }

    private Predicate toOrPredicate(Root<T> root,CriteriaBuilder cb){
        Predicate restrictions = null;

        for(Filter filter:orList){
            if(filter == null){
                continue;
            }
            String property = filter.getProperty();
            Object value = filter.getValue();
            String operator = filter.getOperator();

            switch (operator){
                case "eq":
                    if(restrictions == null){
                        restrictions = cb.equal(root.get(property),value);
                    }
                    else{
                        restrictions = cb.or(restrictions,cb.equal(root.get(property),value));
                    }
                    break;
                case "ne":
                    if(restrictions == null){
                        restrictions = cb.notEqual(root.get(property),value);
                    }
                    else{
                        restrictions = cb.or(restrictions,cb.notEqual(root.get(property),value));
                    }
                    break;
                case "gt":
                    if(restrictions == null){
                        restrictions = cb.gt(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.or(restrictions,cb.gt(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "lt":
                    if(restrictions == null){
                        restrictions = cb.lt(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.or(restrictions,cb.lt(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "ge":
                    if(restrictions == null){
                        restrictions = cb.ge(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.or(restrictions,cb.ge(root.<Number>get(property),(Number)value));
                    }
                    break;
                case "le":
                    if(restrictions == null){
                        restrictions = cb.le(root.<Number>get(property),(Number)value);
                    }
                    else{
                        restrictions = cb.or(restrictions,cb.le(root.<Number>get(property),(Number)value));
                    }
                    break;
            }
        }
        return  restrictions;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate restrictions = null;
        if(!CollectionUtils.isEmpty(andList)){
            restrictions = cb.and(toAndPredicate(root,cb));
            if(!CollectionUtils.isEmpty(orList)){
                restrictions = cb.and(restrictions,toOrPredicate(root,cb));
            }
        }
        return restrictions;
    }
}
