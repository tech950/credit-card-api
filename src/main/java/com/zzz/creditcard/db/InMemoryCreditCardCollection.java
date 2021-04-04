package com.zzz.creditcard.db;

import com.zzz.creditcard.model.CreditCard;
import com.zzz.creditcard.model.Name;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCreditCardCollection implements ICreditCardCollection {

    // List storing credit cards
    private List<CreditCard> creditCards = new ArrayList<>();

    public InMemoryCreditCardCollection() {
        creditCards.add(createCreditCard("799273987130505", 3000.00, CreditCard.CurrencyEnum.GBP,
                "Thomas", "Hardy"));
        creditCards.add(createCreditCard("79927398713000", 300.00, CreditCard.CurrencyEnum.GBP,
                "Susan", "Brown"));
    }

    /**
     * Returns the list of credit cards from db
     *
     * @return
     */
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    /**
     * Add a credit card to db
     *
     * @param card
     */
    public void addCreditCard(CreditCard card) {
        creditCards.add(card);
    }

    private CreditCard createCreditCard(String id, double limit,
                                        CreditCard.CurrencyEnum currency,
                                        String firstName, String lastName) {
        return new CreditCard().id(id).balance(0.00).limit(limit).currency(currency)
                .name(new Name().firstName(firstName).lastname(lastName));
    }
}
