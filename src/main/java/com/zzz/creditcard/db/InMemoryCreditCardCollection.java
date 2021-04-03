package com.zzz.creditcard.db;

import com.zzz.creditcard.model.CreditCard;
import com.zzz.creditcard.model.Name;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCreditCardCollection implements ICreditCardCollection {

    private List<CreditCard> creditCards = new ArrayList<>();

    public InMemoryCreditCardCollection() {
        creditCards.add(createCreditCard("434343434", 1000.00, 3000.00, CreditCard.CurrencyEnum.GBP,
                "Susan", "Brown"));
        creditCards.add(createCreditCard("666777999", 1000.00, 3000.00, CreditCard.CurrencyEnum.GBP,
                "Susan", "Brown"));
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void addCreditCard(CreditCard card) {
        creditCards.add(card);
    }

    private CreditCard createCreditCard(String id, double balance, double limit,
                                        CreditCard.CurrencyEnum currency,
                                        String firstName, String lastName) {
        return new CreditCard().id(id).balance(balance).limit(limit).currency(currency)
                .name(new Name().firstName(firstName).lastname(lastName));
    }
}
