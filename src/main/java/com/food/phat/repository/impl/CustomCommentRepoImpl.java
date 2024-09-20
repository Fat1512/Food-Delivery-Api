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
    public CommentProduct saveComment(CommentProduct commentProduct) {
        Comment comment = this.saveComment(commentProduct.getComment(), null, null);
        commentProduct.setComment(comment);
        return em.merge(commentProduct);
    }

    @Override
    public CommentRestaurant saveComment(CommentRestaurant commentRestaurant) {
        Comment comment = this.saveComment(commentRestaurant.getComment(), null, null);
        commentRestaurant.setComment(comment);
        return em.merge(commentRestaurant);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Object[] hierarchyInfo = (Object[]) em.createNativeQuery("select lft, rgt, rgt - lft + 1 from comment where comment_id = :commentId")
                .setParameter("commentId", commentId).getSingleResult();

        em.createNativeQuery("DELETE FROM comment WHERE lft BETWEEN :left AND :right")
                .setParameter("left", hierarchyInfo[0])
                .setParameter("right", hierarchyInfo[1]).executeUpdate();

        em.createNativeQuery("UPDATE comment SET rgt = rgt - :width WHERE rgt > :right")
                .setParameter("width", hierarchyInfo[2])
                .setParameter("right", hierarchyInfo[1]).executeUpdate();

        em.createNativeQuery("UPDATE comment SET lft = lft - :width WHERE lft > :right")
                .setParameter("width", hierarchyInfo[2])
                .setParameter("right", hierarchyInfo[1]).executeUpdate();
    }

    @Override
    public Comment getCommentByLevel(Integer parentId) {
        return null;
    }
}
