package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.PaymentMethod;
import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository  extends JpaRepository<PaymentMethod, Long> {
}
