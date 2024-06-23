package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.Rating;
import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository  extends JpaRepository<Rating, Long> {
}
