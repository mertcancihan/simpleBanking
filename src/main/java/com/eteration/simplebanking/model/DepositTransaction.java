package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Deposit")
public class DepositTransaction extends Transaction {

    @JsonCreator
    public DepositTransaction(@JsonProperty("amount") double amount) {
        super(amount);
    }

    public DepositTransaction() {

    }

    @Override
    public void execute(BankAccount account) {
        account.credit(getAmount());
    }
}