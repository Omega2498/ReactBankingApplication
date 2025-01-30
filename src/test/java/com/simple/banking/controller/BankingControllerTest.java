package com.simple.banking.controller;

import com.simple.banking.model.*;
import com.simple.banking.service.BankingService;
import com.simple.banking.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BankingControllerTest {
	
	 @Mock
	    private CustomerService customerService;

	    @Mock
	    private BankingService bankingService;

	    @InjectMocks
	    private BankingController bankingController;

	    private MockMvc mockMvc;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(bankingController).build();
	    }

	    @Test
	    public void testRegister() {
	        Customer customer = new Customer();
	        ApiResponse apiResponse = new ApiResponse("success", HttpStatus.OK.value(), "Registration successful", "");
	        when(customerService.register(any(Customer.class))).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

	        ResponseEntity<ApiResponse> response = bankingController.register(customer);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("success", response.getBody().getStatus());
	    }

	    @Test
	    public void testEditProfile() {
	        Customer customer = new Customer();
	        ApiResponse apiResponse = new ApiResponse("success", HttpStatus.OK.value(), "Profile updated", "");
	        when(customerService.editProfile(any(Customer.class))).thenReturn(new ResponseEntity<>(apiResponse, HttpStatus.OK));

	        ResponseEntity<ApiResponse> response = bankingController.editProfile(customer);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("success", response.getBody().getStatus());
	    }

	    @Test
	    public void testLoginSuccess() {
	        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@example.com", "password");
	        Customer customer = new Customer();
	        customer.setPassword("password");
	        customer.setAccountNumber(123456L);
	        when(customerService.authenticateUser("test@example.com")).thenReturn(customer);

	        ResponseEntity<ApiResponse> response = bankingController.login(loginRequestDTO);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("success", response.getBody().getStatus());
	    }

	    @Test
	    public void testLoginFailure() {
	        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@example.com", "wrongpassword");
	        Customer customer = new Customer();
	        customer.setPassword("password");
	        when(customerService.authenticateUser("test@example.com")).thenReturn(customer);

	        ResponseEntity<ApiResponse> response = bankingController.login(loginRequestDTO);
	        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	        assertEquals("failure", response.getBody().getStatus());
	    }

	    @Test
	    public void testDoTransaction() {
	        TransactionDto transactionDto = new TransactionDto();
	        when(bankingService.doTransaction(any(TransactionDto.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        ResponseEntity<?> response = bankingController.doTransaction(transactionDto);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }

	    @Test
	    public void testWithdraw() {
	        when(bankingService.withdraw(1L, 100.0)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        ResponseEntity<?> response = bankingController.withdraw(1L, 100.0);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }

	    @Test
	    public void testDeposit() {
	        when(bankingService.deposit(1L, 100.0)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        ResponseEntity<?> response = bankingController.deposit(1L, 100.0);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }

	    @Test
	    public void testHistory() {
	        when(bankingService.getTransactionHistory(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        ResponseEntity<?> response = bankingController.history(1L);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }

	    @Test
	    public void testProfile() {
	        when(bankingService.getProfile(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        ResponseEntity<?> response = bankingController.profile(1L);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }

	    @Test
	    public void testGetBalance() {
	        Customer customer = new Customer();
	        customer.setCurrentbalance(1000.0);
	        when(customerService.getCustomer(123456L)).thenReturn(Optional.of(customer));

	        ResponseEntity<?> response = bankingController.getBalance(123456L);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(1000.0, response.getBody());
	    }

}
