package com.zzz.banking.creditcard;

import com.zzz.banking.creditcard.api.CreditCardsApi;
import com.zzz.banking.creditcard.api.CreditCardsApiDelegate;
import com.zzz.banking.creditcard.model.CreditCard;
import com.zzz.banking.creditcard.model.CreditCards;
import com.zzz.banking.creditcard.model.Name;
import com.zzz.banking.exception.InvalidBalanceException;
import com.zzz.banking.exception.InvalidCardNumberException;
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
        LOGGER.info("Received request to add credit card number: " + creditCard.getCardNumber());
        if (!CreditCardUtils.isValidCreditCard(creditCard.getCardNumber())) {
            throw new InvalidCardNumberException(creditCard.getCardNumber());
        } else if (creditCard.getBalance() != null && creditCard.getBalance() != 0.0) {
            throw new InvalidBalanceException();
        } else {
            LOGGER.info("Credit card added: " + creditCard.getCardNumber());
            creditCardRepository.save(new CreditCardDO(creditCard.getCardNumber(),
                    creditCard.getName().getFirstName(), creditCard.getName().getLastname(),
                    creditCard.getBalance(), creditCard.getLimit(), creditCard.getCurrency().getValue()));
            return new ResponseEntity<>(creditCard, HttpStatus.CREATED);

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
        List<com.zzz.banking.creditcard.model.CreditCard> cards = StreamSupport
                .stream(creditCardRepository.findAll().spliterator(), false)
                .map(x -> new CreditCard().cardNumber(x.getCardNumber())
                        .name(new Name().firstName(x.getFirstName()).lastname(x.getLastName()))
                        .balance(x.getBalance()).limit(x.getMaxLimit())
                        .currency(CreditCard.CurrencyEnum.fromValue(x.getCurrency())))
                .collect(Collectors.toList());
        return new ResponseEntity(new CreditCards().creditCards(cards),
                HttpStatus.OK);
    }
}
