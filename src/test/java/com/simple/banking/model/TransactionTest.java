package com.simple.banking.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TransactionTest {

@Test
public void testTransactionConstructor() {
   
    Customer customer = new Customer("123456789", 123L, "Yashashri", "Vanjari", "992325162", "ysv@gmail.com", "YSV123", "5566", "Pune", 10000.00);

    LocalDateTime timestamp = LocalDateTime.now();
    double closingBalance = 5000.00;
    double deposit = 1000.00;
    double withdraw = 500.00;
    int id = 101;
    String transactionType = "Deposit";

    Transaction transaction = new Transaction(timestamp, closingBalance, deposit, withdraw, customer, id, transactionType);

    assertEquals(timestamp, transaction.getTimestamp());
    assertEquals(closingBalance, transaction.getClosingBalance());
    assertEquals(deposit, transaction.getDeposit());
    assertEquals(withdraw, transaction.getWithdraw());
    assertEquals(customer, transaction.getCustomer());
    assertEquals(id, transaction.getId());
    assertEquals(transactionType, transaction.getTransactionType());
}
}