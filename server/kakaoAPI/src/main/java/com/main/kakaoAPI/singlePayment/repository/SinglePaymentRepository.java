package com.main.kakaoAPI.singlePayment.repository;

import com.main.kakaoAPI.singlePayment.entity.SinglePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinglePaymentRepository extends JpaRepository<SinglePayment, Long> {
}
