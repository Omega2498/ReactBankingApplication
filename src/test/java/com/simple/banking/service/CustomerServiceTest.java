package com.simple.banking.service;

import com.simple.banking.model.ApiResponse;
import com.simple.banking.model.Customer;
import com.simple.banking.repository.CustomerRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import org.checkerframework.checker.units.qual.m;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private CustomerRepository customerRepository;

    private static Customer getCustomer() {
        Customer mockCustomer = new Customer();
        mockCustomer.setFirstName("John");
        mockCustomer.setContact("9876543210");
        mockCustomer.setEmailId("john.doe@example.com");
        mockCustomer.setPanNumber("FWXPS8807P");
        mockCustomer.setAadharNumber("123456789012");
        mockCustomer.setPassword("pwgfjhdvgfd");
        mockCustomer.setAccountNumber(1000000000L);
        return mockCustomer;
    }

    @Test
    public void testAuthenticateUser() throws Exception {

        Customer mockCustomer = getCustomer();
        Optional<Customer> optionalCustomer = Optional.of(mockCustomer);
        when(customerRepository.findByEmailId("john.doe@example.com"))
                .thenReturn(optionalCustomer);

        Customer result = customerService.authenticateUser("john.doe@example.com");
        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getEmailId());
        assertEquals("John", result.getFirstName());
    }
    @Test
    public void testRegisterSuccess() throws Exception {

        Customer mockCustomer = getCustomer();
        ApiResponse mockresponse = new ApiResponse("success", HttpStatus.OK.value(), "Registration successful", mockCustomer.getAccountNumber().toString());
        ResponseEntity<ApiResponse> responseEntity = ResponseEntity.ok(mockresponse);
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);
        ResponseEntity<ApiResponse> response = customerService.register(mockCustomer);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseEntity.getBody().getMessage(), response.getBody().getMessage());
        assertEquals(responseEntity.getBody().getAccountNumber(), response.getBody().getAccountNumber());  
        assertEquals(responseEntity.getBody().getStatus(),response.getBody().getStatus());
        verify(customerRepository, times(1)).save(any(Customer.class));
        
    }

	/*
	 * @Test public void testRegisterConstraintViolation() { Customer mockCustomer =
	 * getCustomer(); ConstraintViolationException constraintViolationException =
	 * new ConstraintViolationException("Constraint Violation",
	 * Collections.emptySet());
	 * when(customerRepository.save(any(Customer.class))).thenThrow(
	 * constraintViolationException); ResponseEntity<ApiResponse> response =
	 * customerService.register(mockCustomer); assertNotNull(response);
	 * assertEquals(400, response.getStatusCodeValue());
	 * assertTrue(response.getBody().getMessage().contains("Constraint Violation"));
	 * verify(customerRepository, times(1)).save(any(Customer.class)); }
	 */
    
    @Test
    public void testRegisterDataIntegrityViolationException() {
        Customer mockCustomer = new Customer();
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Email ID already exists");
        when(customerRepository.save(any(Customer.class))).thenThrow(ex);

        ResponseEntity<ApiResponse> response = customerService.register(mockCustomer);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error", response.getBody().getStatus());
        assertEquals("Email ID already exists. Please use a different email.", response.getBody().getMessage());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test public void testRegisterException() {
        Customer mockCustomer = getCustomer();
        Exception ex = new RuntimeException("Unexpected Error");
        when(customerRepository.save(any(Customer.class))).thenThrow(ex);
        ResponseEntity<ApiResponse> response = customerService.register(mockCustomer);
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().getMessage().contains("An unexpected error occurred"));
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetCustomer() {
        Long accountNumber = 1000000000L;
        Customer mockCustomer = getCustomer();
        Optional<Customer> optionalCustomer = Optional.of(mockCustomer);
        when(customerRepository.findById(accountNumber)).thenReturn(optionalCustomer);
        Optional<Customer> result = customerService.getCustomer(accountNumber);
        assertTrue(result.isPresent()); assertEquals(accountNumber, result.get().getAccountNumber());
        assertEquals("John", result.get().getFirstName());
        assertEquals("john.doe@example.com", result.get().getEmailId());
        verify(customerRepository, times(1)).findById(accountNumber);
        }
    @Test
    public void testGetCustomerNotFound() {
        Long accountNumber = 123456L;
        when(customerRepository.findById(accountNumber)).thenReturn(Optional.empty());
        Optional<Customer> result = customerService.getCustomer(accountNumber);
        assertFalse(result.isPresent());
        verify(customerRepository, times(1)).findById(accountNumber);
        }
    }


