package com.zzz.banking.creditcard;

import java.util.regex.Pattern;

public class CreditCardUtils {

    private CreditCardUtils() {
    }

    /**
     * Returns true if a credit card is valid This method checks whether the credit card number is numeric, checks
     * whether the length of the credit card is between 13 and 19, and satisfies the Luhn's algorithm.
     *
     * @param cardNumber
     * @return
     */
    public static boolean isValidCreditCard(String cardNumber) {
        return Pattern.matches("[0-9]{13,19}", cardNumber) && isLuhnChecksValid(cardNumber);
    }

    /**
     * This method checks whether the Luhns checks are valid for this card number.
     * </br> 1. Double the value of every second digit
     * </br> 2. If the value is greater then 9, then add up the digits
     * </br> 3. Add the sum of numbers in the odd number places and the sum of digits in step 2
     * </br> 4. If the above sum modulo 10 is 0, the card number is valid.
     *
     * @param cardNumber
     * @return
     */
    private static boolean isLuhnChecksValid(String cardNumber) {
        int sum = 0;
        boolean oddDigit = true;
        for (int x = 0; x < cardNumber.length(); x++) {
            if (oddDigit) {
                sum += Integer.parseInt(cardNumber.substring(x, x + 1));
            } else {
                int x2 = Integer.parseInt(cardNumber.substring(x, x + 1)) * 2;
                sum += (x2 / 10 + x2 % 10);
            }
            oddDigit = !oddDigit;
        }
        return sum % 10 == 0;
    }


}
