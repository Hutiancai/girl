package com.firstboot.repository;

import com.firstboot.domain.Filter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class Query<T> implements Specification<T> {

    private List<Filter> andList = new ArrayList<>();

    private List<Filter> orList = new ArrayList<>();

    private List<String> columnNameList = new ArrayList<>();

    private List<JoinType> joinTypeList = new ArrayList<>();

    private Join join = null;

    public Query and(Filter ...filters){
        for(Filter newfilters : filters){
            newfilters.setAndOrFlag(Filter.Operator.and);
            andList.add(newfilters);
        }
        return this;
    }

    public Query or(Filter ...filters){
        for(Filter newfilters:filters){
            newfilters.setAndOrFlag(Filter.Operator.or);
            orList.add(newfilters);
        }
        return this;
    }

    public Query join(String name, JoinType joinType){
        columnNameList.add(name);
        joinTypeList.add(joinType);
        return this;
    }

    public Predicate initializePredicate(Root<T> root, CriteriaBuilder cb, List<Filter> list){
        Predicate restrictions = null;
        for(Filter filter : list){
            if(restrictions == null){
                restrictions = getPredicateByOperator(filter, restrictions, root, cb);
            }else{
                if(filter.getAndOrFlag()==Filter.Operator.and){
                    restrictions = cb.and(restrictions, getPredicateByOperator(filter, restrictions, root, cb));
                }else if(filter.getAndOrFlag()==Filter.Operator.or){
                    restrictions = cb.or(restrictions, getPredicateByOperator(filter, restrictions, root, cb));
                }
            }
        }
        return restrictions;
    }

    public Predicate getPredicateByOperator(Filter filter, Predicate restrictions, Root<T> root, CriteriaBuilder cb){
        Path path = getPath(root, filter);
        switch(filter.getOperator()){
            case eq:
                restrictions = cb.equal(path, filter.getValue());
                break;
            case ne:
                restrictions = cb.notEqual(path, filter.getValue());
                break;
            case gt:
                restrictions = cb.gt(path, (Number)filter.getValue());
                break;
            case lt:
                restrictions = cb.lt(path, (Number)filter.getValue());
                break;
            case ge:
                restrictions = cb.ge(path, (Number)filter.getValue());
                break;
            case le:
                restrictions = cb.le(path, (Number)filter.getValue());
                break;
            case in:
                restrictions = cb.in(path).value(filter.getValue());
                break;
            case isNull:
                restrictions = path.isNull();
                break;
            case isNotNull:
                restrictions = path.isNotNull();
                break;
            case like:
                restrictions = cb.like(path, (String)filter.getValue());
                break;
            case between:
                ArrayList valueList = (ArrayList)filter.getValue();
                restrictions = cb.between(path, (Comparable)valueList.get(0),(Comparable)valueList.get(1));
                break;
        }
        return restrictions;
    }

    /*public Predicate toAndOrPredicate(Root<T> root, CriteriaBuilder cb){
        Predicate restrictions = null;
        for(Filter filter:list){
            if(filter == null){
                continue;
            }
            restrictions = initializePredicate(filter, restrictions, root, cb);
            }

        return restrictions;
    }*/

    public Path getPath(Root root, Filter filter){
        String[] valueArray = filter.getProperty().split(".");
        if(valueArray.length > 1){
            if(columnNameList.size() > 0){
                for(int i=1; i < columnNameList.size(); i++){
                    join = root.join(columnNameList.get(i), joinTypeList.get(i));
                }
            }
            return root.get(valueArray[0]).get(valueArray[1]);
        }else{
            return root.get(valueArray[0]);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //Join<T, ItemSmallType> join = root.join("smallType",JoinType.LEFT);
        //Join<T, Customer> join1 = root.join("customer",JoinType.LEFT);
        //predicate = criteriaBuilder.equal(join1.get("customer").get("note"),"2222");
       /* toAndOrPredicate(root, cb);
        Predicate restrictions = null;*/

        /*join = root.join("clothes",JoinType.LEFT);*/
        /*join.join("a",JoinType.LEFT);*/
       Predicate restrictions = null;
        if(!CollectionUtils.isEmpty(andList)){
            restrictions = initializePredicate(root, cb, andList);
            if(!CollectionUtils.isEmpty(orList)){
                restrictions = cb.and(restrictions,initializePredicate(root, cb, orList));
            }
        }
        else if(!CollectionUtils.isEmpty(orList)){
            restrictions = initializePredicate(root, cb, orList);
        }

        return restrictions;
    }
}


