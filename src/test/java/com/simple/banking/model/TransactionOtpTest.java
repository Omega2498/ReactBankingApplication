package com.simple.banking.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TransactionOtpTest {

	@Test
	void test() {
		TransactionOtp transactionOtp = new TransactionOtp(LocalDateTime.now(), "123456", 5000.00, 1000.00, 500.00, 1L, 101L, "Deposit");
		transactionOtp.setId(1234L);
		assertEquals(1234L, transactionOtp.getId());
	}

}
