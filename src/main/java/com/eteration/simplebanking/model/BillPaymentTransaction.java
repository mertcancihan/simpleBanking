package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BillPayment")
public class BillPaymentTransaction extends Transaction {
    private String payee;

    @JsonCreator
    public BillPaymentTransaction(@JsonProperty("payee") String payee, @JsonProperty("amount") double amount) {
        super(amount);
        this.payee = payee;
    }

    public BillPaymentTransaction() {

    }

    @Override
    public void execute(BankAccount account) {
        if (getAmount() > account.getBalance()) {
            throw new RuntimeException("Yetersiz bakiye!");
        }
        account.debit(getAmount());
    }

    public String getPayee() {
        return payee;
    }

    @Override
    public String toString() {
        return "Fatura Ödeme: " + payee + ", Tutar: " + getAmount() + ", Tarih: " + getDate();
    }
}
