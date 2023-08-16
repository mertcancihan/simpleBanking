package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.eteration.simplebanking.controller.*;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.*;

import com.eteration.simplebanking.utils.TransactionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private BankAccountController controller;
    @Mock
    private BankAccountService service;

    @Test
    public void givenId_GetAccount_thenReturnJson() throws Exception {
        BankAccount account = new BankAccount("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<BankAccount> result = controller.getAccount( "17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }

    @Test
    public void testCreditEndpoint() throws Exception {
        BankAccount account = new BankAccount("Kerem Karaca", "17892");
        when(service.findAccount("17892")).thenReturn(account);
        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        verify(service, times(1)).findAccount("17892");
        verify(service, times(1)).credit("17892", 1000.0);
        assertNotNull(result.getBody());
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    public void testDebitEndpoint() throws Exception {
        BankAccount mockAccount = new BankAccount("Kerem Karaca", "17892");
        when(service.findAccount("17892")).thenReturn(mockAccount);
        ResponseEntity<TransactionStatus> result = controller.debit("17892", new WithdrawalTransaction(500.0));
        verify(service, times(1)).findAccount("17892");
        verify(service, times(1)).debit("17892", 500.0);
        assertNotNull(result.getBody());
        assertEquals("OK", result.getBody().getStatus());
    }

}
