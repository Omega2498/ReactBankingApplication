package com.simple.banking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestDTOTest {

@Test
    public void testUser()
{
    LoginRequestDTO actualRequest = new LoginRequestDTO();
    actualRequest.setEmail("ysv@gmail.com");
    actualRequest.setPassword("1234");
    assertEquals("ysv@gmail.com",actualRequest.getEmail());
    assertEquals("1234",actualRequest.getPassword());
}



}
