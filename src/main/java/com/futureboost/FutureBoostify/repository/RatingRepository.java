package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.Rating;
import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RatingRepository  extends JpaRepository<Rating, Long> {
}
