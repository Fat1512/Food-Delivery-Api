package com.food.phat.repository.impl;

import com.food.phat.entity.Category;
import com.food.phat.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private EntityManager em;

    @Autowired
    public CategoryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Category> getAllCategories() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Category> q = b.createQuery(Category.class);
        Root r = q.from(Category.class);
        q.select(r);

        Query query = em.createQuery(q);
        List<Category> categoryList = query.getResultList();
        return categoryList;    }

    @Override
    public Category getCategoryById(int categoryId) {
        Query q = em.createQuery("FROM Category WHERE productCategoryId = :categoryId");
        q.setParameter("categoryId", categoryId);
        return (Category) q.getSingleResult();
    }

    @Override
    public Category getCategoryByName(String name) {
        Query q = em.createQuery("FROM Category WHERE name = :name");
        q.setParameter("name", name);
        return (Category) q.getSingleResult();
    }

    @Override
    public Category save(Category category) {
        return em.merge(category);
    }
}
