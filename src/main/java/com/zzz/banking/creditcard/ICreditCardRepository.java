package com.zzz.banking.creditcard;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICreditCardRepository extends CrudRepository<CreditCard, String> {

    Optional<CreditCard> findById(String id);

    Iterable<CreditCard> findAll();

    CreditCard save(CreditCard card);
}