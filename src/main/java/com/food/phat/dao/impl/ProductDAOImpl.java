package com.food.phat.dao.impl;

import com.food.phat.entity.Product;
import com.food.phat.dao.ProductDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private EntityManager em;

    @Autowired
    public ProductDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     *
     * @param params
     * @return
     *     currentPage;
     *     sizePerPage;
     *     totalElements;
     *     totalPages;
     *     last;
     */
    @Override
    public Page<Product> getAllProducts(Map<String, String> params) {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);

        Root r = q.from(Product.class);
        q.select(r);
        Query query1 = em.createQuery(q);
        int sizez = query1.getResultList().size();

        List<Predicate> predicates = new ArrayList<>();
        int size = Integer.parseInt(params.get("size"));
        int page = Integer.parseInt(params.get("page"));

        String fromPrice = params.get("fromPrice");
        if (fromPrice != null && !fromPrice.isEmpty()) {
            predicates.add(b.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
        }

        String toPrice = params.get("toPrice");
        if (toPrice != null && !toPrice.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }

        String cateId = params.get("categoryId");
        if (cateId != null && !cateId.isEmpty()) {
            predicates.add(b.equal(r.get("category"), Integer.parseInt(cateId)));
        }

        Query query2 = em.createQuery(q);
        List<Product> products = query2.getResultList();
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = new PageImpl<>(products, pageable, sizez);

        return productPage;
    }

    @Override
    public Product getProductById(int productId) {
        Query q = em.createQuery("FROM Product WHERE productId = :productId");
        q.setParameter("productId", productId);
        return (Product) q.getSingleResult();
    }

    @Override
    public Product getProductByName(String productName) {
        Query q = em.createQuery("FROM Product WHERE name = :productName");
        q.setParameter("productName", productName);
        return (Product) q.getSingleResult();
    }

    @Override
    public Product save(Product product) {
        return em.merge(product);
    }
}
