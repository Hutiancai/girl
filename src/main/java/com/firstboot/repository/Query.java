package com.firstboot.repository;

import com.firstboot.domain.Clothes;
import com.firstboot.domain.Filter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Query<T> implements Specification<T> {

    private List<Filter> list = new ArrayList<>();

    private Join<T,Object> join = null;

    public Query and(Filter ...filters){
        for(Filter newfilters : filters){
            newfilters.setAndOrFlag(Filter.Operator.and);
            list.add(newfilters);
        }
        return this;
    }

    public Query or(Filter ...filters){
        for(Filter newfilters:filters){
            newfilters.setAndOrFlag(Filter.Operator.or);
            list.add(newfilters);
        }
        return this;
    }

    public Predicate initializePredicate(Filter filter, Predicate restrictions, Root<T> root, CriteriaBuilder cb){
        if(restrictions == null){
            restrictions = getPredicateByOperator(filter, restrictions, root, cb);
        }else{
            if(filter.getAndOrFlag()==Filter.Operator.and){
                restrictions = cb.and(restrictions, getPredicateByOperator(filter, restrictions, root, cb));
            }else if(filter.getAndOrFlag()==Filter.Operator.or){
                restrictions = cb.or(restrictions, getPredicateByOperator(filter, restrictions, root, cb));
            }
        }
        return restrictions;
    }

    public Predicate getPredicateByOperator(Filter filter, Predicate restrictions, Root<T> root, CriteriaBuilder cb){

        switch(filter.getOperator()){
            case eq:
                restrictions = cb.equal(root.get(filter.getProperty()), filter.getValue());
                break;
            case ne:
                restrictions = cb.notEqual(root.get(filter.getProperty()), filter.getValue());
                break;
            case gt:
                restrictions = cb.gt(root.<Number>get(filter.getProperty()),(Number)filter.getValue());
                break;
            case lt:
                restrictions = cb.lt(root.<Number>get(filter.getProperty()),(Number)filter.getValue());
                break;
            case ge:
                restrictions = cb.ge(root.<Number>get(filter.getProperty()),(Number)filter.getValue());
                break;
            case le:
                restrictions = cb.le(root.<Number>get(filter.getProperty()),(Number)filter.getValue());
                break;
            case in:
                restrictions = cb.in(root.get(filter.getProperty())).value(filter.getValue());
                break;
            case isNull:
                restrictions = root.get(filter.getProperty()).isNull();
                break;
            case isNotNull:
                restrictions = root.get(filter.getProperty()).isNotNull();
                break;
            case like:
                restrictions = cb.like(root.<String>get(filter.getProperty()),(String)filter.getValue());
                break;
            case between:
                ArrayList valueList = (ArrayList)filter.getValue();
                restrictions = cb.between(root.<Comparable>get(filter.getProperty()),(Comparable)valueList.get(0),(Comparable)valueList.get(1));
                break;
        }
        return restrictions;
    }

    public Predicate toAndOrPredicate(Root<T> root, CriteriaBuilder cb){
        Predicate restrictions = null;
        for(Filter filter:list){
            if(filter == null){
                continue;
            }
            restrictions = initializePredicate(filter, restrictions, root, cb);
            }

        return restrictions;
    }

    public Join getJoin(Root<T> root, String name, JoinType joinType){
        join = root.join("name", joinType);
        return join;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //Join<T, ItemSmallType> join = root.join("smallType",JoinType.LEFT);
        //Join<T, Customer> join1 = root.join("customer",JoinType.LEFT);
        //predicate = criteriaBuilder.equal(join1.get("customer").get("note"),"2222");
       /* toAndOrPredicate(root, cb);
        Predicate restrictions = null;*/

        Join<T, Clothes> join1 = root.join("clothes",JoinType.LEFT);
        Predicate restrictions = cb.equal(join1.get("c_color"),"red");
        return restrictions;
    }
}


