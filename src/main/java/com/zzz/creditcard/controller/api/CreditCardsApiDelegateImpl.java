package com.zzz.creditcard.controller.api;

import com.zzz.creditcard.api.CreditCardsApi;
import com.zzz.creditcard.api.CreditCardsApiDelegate;
import com.zzz.creditcard.db.ICreditCardRepository;
import com.zzz.creditcard.model.CreditCard;
import com.zzz.creditcard.model.CreditCards;
import com.zzz.creditcard.model.Error;
import com.zzz.creditcard.model.Name;
import com.zzz.creditcard.utils.CreditCardUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CreditCardsApiDelegateImpl implements CreditCardsApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardsApiDelegateImpl.class);

    @Autowired
    private ICreditCardRepository creditCardRepository;

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
            creditCardRepository.save(new com.zzz.creditcard.db.CreditCard(creditCard.getId(),
                    creditCard.getName().getFirstName(), creditCard.getName().getLastname(),
                    creditCard.getBalance(), creditCard.getLimit(), creditCard.getCurrency().getValue()));
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
        List cards = StreamSupport.stream(creditCardRepository.findAll().spliterator(), false)
                .map(x -> new CreditCard().id(x.getId())
                        .name(new Name().firstName(x.getFirstName()).lastname(x.getLastName()))
                        .balance(x.getBalance()).limit(x.getMaxLimit())
                        .currency(CreditCard.CurrencyEnum.fromValue(x.getCurrency())))
                .collect(Collectors.toList());
        return new ResponseEntity(new CreditCards().creditCards(cards),
                HttpStatus.OK);
    }
}
