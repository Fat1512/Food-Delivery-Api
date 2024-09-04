package com.food.phat.dao.impl;

import com.food.phat.entity.ProductCategory;
import com.food.phat.dao.CategoryDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private EntityManager em;

    @Autowired
    public CategoryDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> q = b.createQuery(ProductCategory.class);
        Root r = q.from(ProductCategory.class);
        q.select(r);

        Query query = em.createQuery(q);
        List<ProductCategory> productCategoryList = query.getResultList();
        return productCategoryList;    }

    @Override
    public ProductCategory getCategoryById(int categoryId) {
        Query q = em.createQuery("FROM ProductCategory WHERE productCategoryId = :categoryId");
        q.setParameter("categoryId", categoryId);
        return (ProductCategory) q.getSingleResult();
    }

    @Override
    public ProductCategory getCategoryByName(String name) {
        Query q = em.createQuery("FROM ProductCategory WHERE name = :name");
        q.setParameter("name", name);
        return (ProductCategory) q.getSingleResult();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return em.merge(productCategory);
    }
}
