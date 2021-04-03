package com.zzz.creditcard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardUtilsTest {

    @Test
    public void testIsValidCreditCard() {
        Assertions.assertTrue(CreditCardUtils.isValidCreditCard("79927398713"));
    }
}
