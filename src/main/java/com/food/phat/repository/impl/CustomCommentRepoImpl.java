package com.food.phat.repository.impl;

import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomCommentRepoImpl implements CustomCommentRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment saveComment(Comment comment, Integer leftParent, Integer rightParent) {
        Integer left = 0;
        if(leftParent != null)
            left = (Integer) em.createNativeQuery("SELECT lft FROM comment where lft = :left")
                .setParameter("left", leftParent).getSingleResult();

        em.createNativeQuery("UPDATE comment SET rgt = rgt + 2 WHERE rgt > :left")
                .setParameter("left", left).executeUpdate();

        em.createNativeQuery("UPDATE comment SET lft = lft + 2 WHERE lft > :left")
                .setParameter("left", left).executeUpdate();


        comment.setLft(left + 1);
        comment.setRgt(left + 2);
        return em.merge(comment);
    }

    @Override
    public CommentProduct saveComment(CommentProduct commentProduct, Integer leftParent, Integer rightParent) {
        Comment comment = this.saveComment(commentProduct.getComment(), null, null);
        commentProduct.setComment(comment);
        return em.merge(commentProduct);
    }

    @Override
    public CommentRestaurant saveComment(CommentRestaurant commentRestaurant, Integer leftParent, Integer rightParent) {
        Comment comment = this.saveComment(commentRestaurant.getComment(), null, null);
        commentRestaurant.setComment(comment);
        return em.merge(commentRestaurant);
    }
}
