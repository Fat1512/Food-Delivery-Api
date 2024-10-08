package com.food.phat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@Table(name="product_category")
@Entity @DynamicUpdate
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_category_id")
    private Integer productCategoryId;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="restaurant_fkey")
    Restaurant restaurant;
    public ProductCategory() {

    }
}
