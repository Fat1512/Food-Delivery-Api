package com.food.phat.repository;

import com.food.phat.entity.CommentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentHistoryRepository extends JpaRepository<CommentHistory, Integer> {
}
