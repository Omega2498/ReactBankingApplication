package com.simple.banking.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

@Test
public void test() 
{
        Customer customer = new Customer("123456789", 123L, "Yashashri", "Vanjari", "992325162", "ysv@gmail.com", "YSV123", "5566", "Pune", 10000.00);
        
		Customer actualRequest = new Customer();
	    actualRequest.setAadharNumber("123456789");
	    actualRequest.setAccountNumber(123L);
	    actualRequest.setAddress("Pune");
	    actualRequest.setContact("992325162");
	    actualRequest.setCurrentbalance(10000);
	    actualRequest.setEmailId("ysv@gmail.com");
	    actualRequest.setFirstName("Yashashri");
	    actualRequest.setLastName("Vanjari");
	    actualRequest.setPanNumber("5566");
	    actualRequest.setPassword("YSV123");
	    
	    assertEquals("123456789",actualRequest.getAadharNumber());
	    assertEquals(123L,actualRequest.getAccountNumber());
	    assertEquals("Pune",actualRequest.getAddress());
	    assertEquals("992325162",actualRequest.getContact());
	    assertEquals(10000,actualRequest.getCurrentbalance());
	    assertEquals("ysv@gmail.com",actualRequest.getEmailId());
	    assertEquals("Yashashri",actualRequest.getFirstName());
	    assertEquals("Vanjari",actualRequest.getLastName());
	    assertEquals("5566",actualRequest.getPanNumber());
	    assertEquals("YSV123",actualRequest.getPassword());
	    
	    
	}

}
