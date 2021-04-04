package com.zzz.creditcard.controller.api;

import com.zzz.creditcard.api.CreditCardsApi;
import com.zzz.creditcard.api.CreditCardsApiDelegate;
import com.zzz.creditcard.db.ICreditCardCollection;
import com.zzz.creditcard.model.CreditCard;
import com.zzz.creditcard.model.CreditCards;
import com.zzz.creditcard.model.Error;
import com.zzz.creditcard.utils.CreditCardUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditCardsApiDelegateImpl implements CreditCardsApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardsApiDelegateImpl.class);

    @Autowired
    private ICreditCardCollection creditCardCollection;

    /**
     * POST /creditCards : Add a Credit Card
     *
     * @param creditCard (required)
     * @return Successful operation (status code 200) or Bad request (status code 400) or Internal Server error /
     * Technical (status code 500)
     * @see CreditCardsApi#addCreditCard
     */
    public ResponseEntity<CreditCard> addCreditCard(CreditCard creditCard) {
        LOGGER.info("Received request to add credit card id: " + creditCard.getId());
        if (!CreditCardUtils.isValidCreditCard(creditCard.getId())) {
            String message = String.format("The Credit Card number %s is invalid.", creditCard.getId());
            LOGGER.info(message);
            return new ResponseEntity(new Error().code(HttpStatus.BAD_REQUEST.value())
                    .message(message),
                    HttpStatus.BAD_REQUEST);
        } else if (creditCard.getBalance() != null && creditCard.getBalance() != 0.0) {
            String message = String.format("The Credit Card balance %s should be 0.", creditCard.getBalance());
            LOGGER.info(message);
            return new ResponseEntity(new Error().code(HttpStatus.BAD_REQUEST.value())
                    .message(message),
                    HttpStatus.BAD_REQUEST);
        } else {
            LOGGER.info("Credit card added, id: " + creditCard.getId());
            creditCardCollection.addCreditCard(creditCard);
            return new ResponseEntity<>(creditCard, HttpStatus.OK);
        }

    }

    /**
     * GET /creditCards : List Credit Cards
     *
     * @return Successful operation (status code 200) or Bad request (status code 400) or Internal Server error /
     * Technical (status code 500)
     * @see CreditCardsApi#getCreditCards
     */
    public ResponseEntity<CreditCards> getCreditCards() {
        LOGGER.info("Retrieve the list of Credit cards ");
        return new ResponseEntity(new CreditCards().creditCards(creditCardCollection.getCreditCards()),
                HttpStatus.OK);
    }
}
