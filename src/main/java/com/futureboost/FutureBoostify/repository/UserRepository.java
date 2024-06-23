package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface  UserRepository  extends JpaRepository<User, Long> {
}
