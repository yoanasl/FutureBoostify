package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.PaymentMethod;
import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository  extends JpaRepository<PaymentMethod, Long> {
}
