package com.food.phat.repository.impl;

import com.food.phat.entity.Comment;
import com.food.phat.entity.CommentProduct;
import com.food.phat.entity.CommentRestaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

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
    public List<Comment> getCommentByLevel(Integer parentId) {

        List commentList = getNextCommentLevel(parentId);
        List<Integer> commentIds = commentList.stream().map(comment -> ((Object[]) comment)[0]).toList();

        return em.createQuery("select c from Comment c where commentId in :commentIds", Comment.class).setParameter("commentIds", commentIds).getResultList();
    }

    private List getNextCommentLevel(Integer parentId) {
        return em.createNativeQuery(
                """
                            SELECT node.comment_id ,(COUNT(parent.content) - (sub_tree.depth + 1)) AS depth
                            FROM    comment AS node,
                                    comment AS parent,
                                    comment AS sub_parent,
                                    (
                                            SELECT node.content, (COUNT(parent.content) - 1) AS depth
                                            FROM comment AS node,
                                            comment AS parent
                                            WHERE node.lft BETWEEN parent.lft AND parent.rgt
                                            AND node.comment_id = :parent_id
                                            GROUP BY node.content
                                            order by node.lft
                                    ) AS sub_tree
                            WHERE node.lft BETWEEN parent.lft AND parent.rgt
                                    AND node.lft BETWEEN sub_parent.lft AND sub_parent.rgt
                                    AND sub_parent.content = sub_tree.content
                            GROUP BY node.comment_id
                            HAVING depth = 1
                            order by node.lft
                        """
        ).setParameter("parent_id", parentId).getResultList();
    }
}
