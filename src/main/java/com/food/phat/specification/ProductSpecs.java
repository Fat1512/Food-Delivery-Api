package com.food.phat.specification;

import com.food.phat.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {

    public static Specification<Product> categoryIdEquals(Integer categoryId) {
        return (root, query, builder) ->
                categoryId == null ?
                        builder.conjunction() : builder.equal(root.get("category").get("categoryId"), categoryId);
    }
}
