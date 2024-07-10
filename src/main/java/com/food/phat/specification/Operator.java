package com.food.phat.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;
import java.util.List;

public enum Operator {
    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            if(request.getValue() == null) return cb.conjunction();
            Expression<?> key= request.setExpression(root);
            return cb.and(cb.equal(key, request.getValue()), predicate);
        }
    },
    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            if(request.getValue() == null) return cb.conjunction();
            Expression<?> key = request.setExpression(root);
            return cb.and(cb.notEqual(key, request.getValue()), predicate);
        }
    },
    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            if(request.getValue() == null) return cb.conjunction();
            Expression<String> key = request.setExpression(root);
            return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
        }
    },
    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {

            List<Object> values = request.getValues();
            if(values.isEmpty() || values == null) return cb.conjunction();
            CriteriaBuilder.In<Object> inClause = cb.in(request.setExpression(root));
            for (Object value : values) {
                inClause.value(value);
            }
            return cb.and(inClause, predicate);
        }
    },
    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getValue();
            Object valueTo = request.getValueTo();
            if(valueTo == null || value == null) return cb.conjunction();

            if (request.getValue().getClass() == LocalDateTime.class) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = request.setExpression(root);
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getValue().getClass() != Character.class && request.getValue().getClass() != Boolean.class) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                return cb.and(cb.and(cb.ge(request.setExpression(root), start), cb.le(request.setExpression(root), end)), predicate);
            }
            return predicate;
        }
    };
    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

}
