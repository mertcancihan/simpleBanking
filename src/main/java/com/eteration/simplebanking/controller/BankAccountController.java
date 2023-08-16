package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.utils.TransactionStatus;
import com.eteration.simplebanking.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/account/v1")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/create")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdAccount = bankAccountService.createAccount(bankAccount);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) {
        BankAccount existingAccount = bankAccountService.findAccount(accountNumber);
        bankAccountService.credit(existingAccount.getAccountNumber(), depositTransaction.getAmount());
        TransactionStatus response = new TransactionStatus();
        response.setStatus("OK");
        response.setApprovalCode(UUID.randomUUID().toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) {
        BankAccount existingAccount = bankAccountService.findAccount(accountNumber);
        bankAccountService.debit(existingAccount.getAccountNumber(), withdrawalTransaction.getAmount());

        TransactionStatus response = new TransactionStatus();
        response.setStatus("OK");
        response.setApprovalCode(UUID.randomUUID().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(bankAccountService.findAccount(accountNumber));
    }

    @PostMapping("/paybill/{accountNumber}")
    public ResponseEntity<TransactionStatus> payBill(@PathVariable String accountNumber, @RequestBody BillPaymentTransaction billPaymentTransaction) {
        BankAccount existingAccount = bankAccountService.findAccount(accountNumber);
        bankAccountService.payBill(existingAccount.getAccountNumber(), billPaymentTransaction.getPayee(), billPaymentTransaction.getAmount());

        TransactionStatus response = new TransactionStatus();
        response.setStatus("OK");
        response.setApprovalCode(UUID.randomUUID().toString());
        return ResponseEntity.ok(response);
    }
}


