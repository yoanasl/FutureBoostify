package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findAllByEmail(String email);
    List<User> findAll();

}