package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Withdrawal")
public class WithdrawalTransaction extends Transaction {

    @JsonCreator
    public WithdrawalTransaction(@JsonProperty("amount") double amount) {
        super(amount);
    }

    public WithdrawalTransaction() {

    }

    @Override
    public void execute(BankAccount account) {
        if (getAmount() > account.getBalance()) {
            throw new RuntimeException("Yetersiz bakiye!");
        }
        account.debit(getAmount());
    }
}