package com.simple.banking.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransactionDtoTest {

	@Test
	void test() {
		TransactionDto actualRequest = new TransactionDto(1234L,"Success");
	    actualRequest.setCustomerId(1234L);
	    actualRequest.setCode("Success");
	    assertEquals(1234L,actualRequest.getCustomerId());
	    assertEquals("Success",actualRequest.getCode());
	}

}
