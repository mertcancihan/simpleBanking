package com.eteration.simplebanking;

import com.eteration.simplebanking.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    @Test
    public void testBankAccountTransactions() {
        BankAccount account = new BankAccount("Jim", "12345");
        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new BillPaymentTransaction("Vodafone", 96.50));
        assertEquals(account.getBalance(), 703.50, 0.0001);
    }
}
