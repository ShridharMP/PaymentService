package com.patil.software.solutions.PaymentService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patil.software.solutions.PaymentService.model.PaymentRequest;
import com.patil.software.solutions.PaymentService.model.PaymentResponse;
import com.patil.software.solutions.PaymentService.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
		return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable(name = "orderId") long orderId) {
		return new ResponseEntity<PaymentResponse>(paymentService.getPaymentDetails(orderId), HttpStatus.OK);
	}
}
