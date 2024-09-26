package com.food.phat.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import java.util.List;

@Getter
@Setter
@Table(name="product")
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private Boolean status;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private float price;

    @Column(name="thumbnail")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name="restaurant_fkey")
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name="product_modifier_group",
            joinColumns = @JoinColumn(name="product_fkey"),
            inverseJoinColumns = @JoinColumn(name="modifier_group_fkey")
    )
    private List<ModifierGroup> modifierGroups;

    @ManyToOne
    @JoinColumn(name="product_category_fkey")
    private ProductCategory productCategory;

    @ManyToMany(mappedBy = "products")
    private List<MenuCategory> menuCategories;

    public void addMenuCategeory(MenuCategory menuCategory) {
        this.menuCategories.add(menuCategory);
    }

    public void removeCategory(Integer categoryId) {
        this.menuCategories.removeIf(menuCategory -> menuCategory.getMenuCategoryId() == categoryId);
    }
}
