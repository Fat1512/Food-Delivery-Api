package com.food.phat.specification;


import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class FilterRequest<T> {
    private String key;
    private Object value;
    private Object valueTo;
    private List<Object> values;

    private Operator operator;
    private Expression<T> expression;

    public abstract Expression setExpression(Root root);
}
