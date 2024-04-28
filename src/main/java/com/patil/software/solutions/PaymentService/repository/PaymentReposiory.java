package com.patil.software.solutions.PaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patil.software.solutions.PaymentService.entity.TransactionDetails;

@Repository
public interface PaymentReposiory extends JpaRepository<TransactionDetails, Long>{

}
