package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.Comment;
import com.futureboost.FutureBoostify.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
