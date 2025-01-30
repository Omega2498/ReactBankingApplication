package com.simple.banking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiResponseTest {

    @Test
    public void testRequestBody()
    {
        ApiResponse actualResponse = new ApiResponse();
        actualResponse.setMessage("Registered");
        actualResponse.setStatus("Success");
        actualResponse.setAccountNumber("12345363");
        actualResponse.setStatusCode(200);
        assertEquals("Registered",actualResponse.getMessage());
        assertEquals("12345363",actualResponse.getAccountNumber());
        assertEquals("Success",actualResponse.getStatus());
        assertEquals(200,actualResponse.getStatusCode());
    }



}
