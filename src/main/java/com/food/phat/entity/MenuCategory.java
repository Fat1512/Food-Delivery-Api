package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@Table(name="menu_category")
@Entity
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_category_id")
    private int menuCategoryId;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.DETACH)
    private List<Product> products;
}
