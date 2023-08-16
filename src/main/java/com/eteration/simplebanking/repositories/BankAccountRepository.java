package com.eteration.simplebanking.repositories;

import com.eteration.simplebanking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}

