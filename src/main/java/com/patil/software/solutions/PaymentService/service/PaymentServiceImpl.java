package com.patil.software.solutions.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patil.software.solutions.PaymentService.entity.TransactionDetails;
import com.patil.software.solutions.PaymentService.model.PaymentMode;
import com.patil.software.solutions.PaymentService.model.PaymentRequest;
import com.patil.software.solutions.PaymentService.model.PaymentResponse;
import com.patil.software.solutions.PaymentService.repository.PaymentReposiory;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentReposiory paymentReposiory;

	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording Payment Detais {} ", paymentRequest);
		TransactionDetails transactionDetails = TransactionDetails.builder().paymentDate(Instant.now())
				.paymentMode(PaymentMode.CREDIT_CARD.toString()).paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId()).referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount()).build();
		paymentReposiory.save(transactionDetails);
		log.info("Transaction Completed with Transactio Id {} ", transactionDetails.getId());
		return transactionDetails.getId();
	}

	@Override
	public PaymentResponse getPaymentDetails(long orderId) {
		log.info("Recording Payment Detais for the orderId{} ", orderId);
		TransactionDetails transactionDetails = paymentReposiory.findByOrderId(orderId);
		log.info("transactionDetails->" + "Amount:" + transactionDetails.getAmount() + "Id:"
				+ transactionDetails.getId() + "OrderId:" + transactionDetails.getOrderId() + "PaymentMode:"
				+ transactionDetails.getPaymentMode() + "PaymentStatus:" + transactionDetails.getPaymentStatus()
				+ "ReferenceNumber:" + transactionDetails.getReferenceNumber() + "PaymentDate:"
				+ transactionDetails.getPaymentDate());

		PaymentResponse paymentResponse = PaymentResponse.builder().paymentId(transactionDetails.getId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.paymentDate(transactionDetails.getPaymentDate()).orderId(transactionDetails.getOrderId())
				.status(transactionDetails.getPaymentStatus()).amount(transactionDetails.getAmount()).build();
		return paymentResponse;
	}
}
