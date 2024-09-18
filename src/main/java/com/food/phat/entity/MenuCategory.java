package com.food.phat.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="menu_category_has_product",
            joinColumns = @JoinColumn(name="menu_category_fkey"),
            inverseJoinColumns  = @JoinColumn(name="product_fkey")
    )
    private List<Product> products;

    @ManyToMany(mappedBy="menuCategories", cascade=CascadeType.MERGE)
    private List<Menu> menus;

    public void addProduct(List<Product> products) {
        if(this.products == null) this.products = new ArrayList<>();
        this.products.addAll(products);
    }

    public void addProduct(Product product) {
        if(this.products == null) this.products = new ArrayList<>();
        this.products.add(product);
        product.addMenuCategeory(this);
    }

    public void removeProduct(List<Integer> productIds) {
        this.products.removeIf(product -> {
            if(productIds.contains(product.getProductId())) {
                product.removeCategory(this.getMenuCategoryId());
                return true;
            }
            return false;
        });
    }

    public void updateProduct(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
    }

    public void updateMenu(List<Menu> menus) {
        this.menus.clear();
        this.menus.addAll(menus);
    }

}









