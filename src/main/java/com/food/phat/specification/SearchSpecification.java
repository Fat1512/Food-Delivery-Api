package com.food.phat.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
    public List<FilterRequest> filters;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterRequest filter : filters) {
//            if(filter.getValue() != null)
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }
        return predicate;
    }
}
