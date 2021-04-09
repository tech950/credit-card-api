package com.zzz.banking.creditcard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CreditCardUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"7992739871ABCD", "79927398713", "79927398713000000000", "79927398713891"})
    public void testInvalidCreditCards(String cardNumber) {
        Assertions.assertFalse(CreditCardUtils.isValidCreditCard(cardNumber));
    }

    @Test
    public void testValidCreditCard() {
        Assertions.assertTrue(CreditCardUtils.isValidCreditCard("371110201010004"));
    }

}
