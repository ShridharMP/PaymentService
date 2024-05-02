package com.patil.software.solutions.PaymentService.service;

import com.patil.software.solutions.PaymentService.model.PaymentRequest;
import com.patil.software.solutions.PaymentService.model.PaymentResponse;

public interface PaymentService{
	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetails(long orderId);
}
