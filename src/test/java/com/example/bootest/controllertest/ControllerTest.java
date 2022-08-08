package com.example.bootest.controllertest;

import com.example.bootest.domailn.model.DTOs.ExchangeDTO;
import com.example.bootest.repository.RatesRepository;
import com.example.bootest.service.BankService;
import com.example.bootest.service.client.RateBank;
import com.example.bootest.service.client.Valute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
  @MockBean BankService bankService;
  @MockBean RatesRepository repository;
  @Autowired private MockMvc mockMvc;

  @Test
  void convert_ShouldReturnTrue() throws Exception {

    ExchangeDTO exchangeDTO = new ExchangeDTO("USD", "EUR", 1.0);
    when(bankService.convert(exchangeDTO)).thenReturn(1.01);

    mockMvc
        .perform(
            get("/exchange?firstCurrencyName=USD&firstCurrencyAmount=1&secondCurrencyName=EUR"))
        .andExpect(status().isOk())
        .andExpect(
            result -> {
              assertEquals("1.01", result.getResponse().getContentAsString());
            });
  }

  @Test
  void putValute_shouldReturnTrue() throws Exception {
    Valute valute = new Valute();
    valute.setCharCode("TUG");
    valute.setNominal(1.0);
    valute.setValue(14.88);
    when(repository.getRateBank()).thenReturn(new RateBank(Map.of(valute.getCharCode(), valute)));
    mockMvc
        .perform(post("/addnew?charcode=TUG&amount=1.0&value=14.88"))
        .andExpect(status().isCreated());
  }
}
