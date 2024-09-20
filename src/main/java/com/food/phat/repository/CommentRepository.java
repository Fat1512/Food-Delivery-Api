package com.food.phat.repository;

import com.food.phat.entity.Comment;
import com.food.phat.repository.impl.CustomCommentRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, CustomCommentRepo {

}
