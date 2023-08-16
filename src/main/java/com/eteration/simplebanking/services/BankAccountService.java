package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createAccount(BankAccount bankAccount) {
        bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber())
                .ifPresent(existingAccount -> {
                    throw new RuntimeException("Bu hesap numarasına sahip bir banka hesabı zaten var!");
                });
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount credit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            throw new RuntimeException("Hesap numarasıyla ilişkili bir banka hesabı bulunamadı!");
        }
        DepositTransaction transaction = new DepositTransaction(amount);
        account.post(transaction);
        return bankAccountRepository.save(account);
    }

    public void debit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        WithdrawalTransaction transaction = new WithdrawalTransaction(amount);
        account.post(transaction);
        bankAccountRepository.save(account);
    }

    public void payBill(String accountNumber, String payee, double amount) {
        BankAccount account = findAccount(accountNumber);
        BillPaymentTransaction transaction = new BillPaymentTransaction(payee, amount);
        account.post(transaction);
        bankAccountRepository.save(account);
    }

    public BankAccount findAccount(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Hesap numarasıyla ilişkili bir banka hesabı bulunamadı!"));
    }
}
