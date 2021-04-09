package com.zzz.banking.creditcard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CreditCardDO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String cardNumber;
    private String firstName;
    private String lastName;
    private Double balance;
    private Double maxLimit;
    private String currency;

    protected CreditCardDO() {
    }

    public CreditCardDO(String cardNumber, String firstName, String lastName, Double balance,
                        Double maxLimit, String currency) {
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.maxLimit = maxLimit;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id='" + id + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", maxLimit=" + maxLimit +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCardNumber() {
        return cardNumber;
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
