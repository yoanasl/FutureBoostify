package com.futureboost.FutureBoostify.repository;

import com.futureboost.FutureBoostify.model.Payment;
import com.futureboost.FutureBoostify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaymentRepository  extends JpaRepository<Payment, Long>  {
}
