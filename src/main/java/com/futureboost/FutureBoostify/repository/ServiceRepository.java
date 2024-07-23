package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ServiceRepository extends JpaRepository<Service, Long > {
}
