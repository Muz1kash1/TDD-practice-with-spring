package com.example.bootest;

import com.example.bootest.domailn.model.DTOs.ExchangeDTO;
import com.example.bootest.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BootestApplicationTests {

  @MockBean BankService bankService;
  @Autowired private MockMvc mockMvc;

  @Test
  void contextLoads() {}

  @Test
  void convert_ShouldReturnTrue() throws Exception {

    ExchangeDTO exchangeDTO = new ExchangeDTO("USD", "EUR", 1.0);
    when(bankService.convert(exchangeDTO)).thenReturn(1.01);
    //    doReturn(1.01).when(bankService.convert(exchangeDTO));

    mockMvc
        .perform(
            get("/exchange?firstCurrencyName=USD&firstCurrencyAmount=1&secondCurrencyName=EUR"))
        .andExpect(status().isOk())
        .andExpect(
            result -> {
              assertEquals("1.01", result.getResponse().getContentAsString());
            });
  }
}
