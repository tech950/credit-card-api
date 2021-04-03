package com.zzz.creditcard.db;

import com.zzz.creditcard.model.CreditCard;

import java.util.Collections;
import java.util.List;

public interface ICreditCardCollection {

    default List<CreditCard> getCreditCards() {
        return Collections.EMPTY_LIST;
    }

    void addCreditCard(CreditCard card);
}
