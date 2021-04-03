package com.zzz.creditcard.controller.api;

import com.zzz.creditcard.api.CreditCardsApiController;
import com.zzz.creditcard.api.CreditCardsApiDelegate;
import com.zzz.creditcard.db.ICreditCardCollection;
import com.zzz.creditcard.model.CreditCard;
import com.zzz.creditcard.model.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CreditCardsApiController.class)
public class CreditCardsApiControllerTest {

    private static final String URL = "/creditCards";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ICreditCardCollection creditCardCollection;

    @Test
    public void testGetCreditCards() throws Exception {
        CreditCard card = new CreditCard().id("8889999").balance(100.00).limit(1000.00)
                .currency(CreditCard.CurrencyEnum.GBP)
                .name(new Name().firstName("Hello").lastname("World"));
        when(creditCardCollection.getCreditCards()).thenReturn(Collections.singletonList(card));
        String expectedResponse = "{\"creditCards\":[{\"id\":\"8889999\"," +
                "\"name\":{\"firstName\":\"Hello\",\"lastname\":\"World\"}," +
                "\"balance\":100.0,\"limit\":1000.0,\"currency\":\"GBP\"}]}";
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testAddCreditCard() throws Exception {
        String request = "{\"id\":\"434343434\"," +
                "\"name\":{\"firstName\":\"Susan\",\"lastname\":\"Brown\"}," +
                "\"balance\":1000.0," +
                "\"limit\":3000.0," +
                "\"currency\":\"GBP\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        public static CreditCardsApiDelegate getCreditCardsApiDelegate() {
            return new CreditCardsApiDelegateImpl();
        }
    }
}
