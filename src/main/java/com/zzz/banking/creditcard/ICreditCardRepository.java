package com.zzz.banking.creditcard;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICreditCardRepository extends CrudRepository<CreditCardDO, String> {

    Optional<CreditCardDO> findById(String id);

    Iterable<CreditCardDO> findAll();

    CreditCardDO save(CreditCardDO card);
}