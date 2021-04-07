package com.zzz.banking.creditcard;

import com.zzz.banking.creditcard.api.CreditCardsApiController;
import com.zzz.banking.creditcard.api.CreditCardsApiDelegate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(value = CreditCardsApiController.class)
public class CreditCardsApiControllerTest {

    private static final String URL = "/creditCards";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ICreditCardRepository creditCardRepository;

    @Test
    public void testGetCreditCards() throws Exception {
        CreditCard card = new CreditCard("79927398713000", "Hello",
                "World", 100.00, 1000.00, "GBP");
        when(creditCardRepository.findAll()).thenReturn(Collections.singletonList(card));
        String expectedResponse = "{\"creditCards\":[{\"id\":\"79927398713000\"," +
                "\"name\":{\"firstName\":\"Hello\",\"lastname\":\"World\"}," +
                "\"balance\":100.0,\"limit\":1000.0,\"currency\":\"GBP\"}]}";
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testAddCreditCard() throws Exception {
        String request = "{\"id\":\"79927398713000\"," +
                "\"name\":{\"firstName\":\"Susan\",\"lastname\":\"Brown\"}," +
                "\"limit\":3000.0," +
                "\"balance\":0.0," +
                "\"currency\":\"GBP\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(request));
    }

    @Test
    public void testAddCreditCardInvalidCardNumberError() throws Exception {
        String request = "{\"id\":\"79927398\"," +
                "\"name\":{\"firstName\":\"Susan\",\"lastname\":\"Brown\"}," +
                "\"limit\":3000.0," +
                "\"balance\":0.0," +
                "\"currency\":\"GBP\"}";
        String expectedResponse = "{\"code\":400,\"message\":\"The Credit Card number 79927398 is invalid.\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testAddCreditCardBalanceError() throws Exception {
        String request = "{\"id\":\"79927398713000\"," +
                "\"name\":{\"firstName\":\"Susan\",\"lastname\":\"Brown\"}," +
                "\"balance\":500.0," +
                "\"limit\":3000.0," +
                "\"currency\":\"GBP\"}";
        String expectedResponse = "{\"code\":400,\"message\":\"The Credit Card balance 500.0 should be 0.\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expectedResponse));
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
