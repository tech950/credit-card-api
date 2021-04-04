package com.zzz.creditcard.controller;

import com.zzz.creditcard.db.ICreditCardCollection;
import com.zzz.creditcard.db.InMemoryCreditCardCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class CreditCardConfiguration {

    @Bean
    @Lazy
    public ICreditCardCollection getCreditCardCollection() {
        return new InMemoryCreditCardCollection();
    }

}
