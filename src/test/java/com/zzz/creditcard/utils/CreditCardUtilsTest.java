package com.zzz.creditcard.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardUtilsTest {

    @Test
    public void testValidCreditCard() {
        Assertions.assertTrue(CreditCardUtils.isValidCreditCard("371110201010004"));
    }

    @Test
    public void testAlphabeticCreditCard() {
        Assertions.assertFalse(CreditCardUtils.isValidCreditCard("7992739871ABCD"));
    }

    @Test
    public void testMinLengthCreditCard() {
        Assertions.assertFalse(CreditCardUtils.isValidCreditCard("79927398713"));
    }

    @Test
    public void testMaxLengthCreditCard() {
        Assertions.assertFalse(CreditCardUtils.isValidCreditCard("79927398713000000000"));
    }

    @Test
    public void testInvalidLuhnCheckCreditCard() {
        Assertions.assertFalse(CreditCardUtils.isValidCreditCard("79927398713891"));
    }

}
