package com.zzz.creditcard.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CreditCard {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Double balance;
    private Double maxLimit;
    private String currency;

    protected CreditCard() {
    }

    public CreditCard(String id, String firstName, String lastName, Double balance, Double limit, String currency) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.maxLimit = limit;
        this.currency = currency;
    }


    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', balance='%s', limit='%s',currency='%s' ]",
                id, firstName, lastName, balance, maxLimit, currency);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public Double getBalance() {
        return balance;
    }

    public Double getMaxLimit() {
        return maxLimit;
    }

    public String getCurrency() {
        return currency;
    }
}
