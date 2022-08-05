package com.example.bootest.domailn.model.DTOs;

public class ExchangeDTOMapper {
  public static ExchangeDTO save(String firstCurrency,
                                 String secondCurrency,
                                 double amount) {
    ExchangeDTO saved = new ExchangeDTO(" ", " ",0.0);
    saved.setFirstCurrency(firstCurrency);
    saved.setSecondCurrency(secondCurrency);
    saved.setAmount(amount);
    return saved;
  }
}
