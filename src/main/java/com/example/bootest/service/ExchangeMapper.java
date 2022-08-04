package com.example.bootest.service;

import com.example.bootest.domailn.model.DTOs.ExchangeDTO;

public class ExchangeMapper {
  public static ExchangeDTO save(ExchangeDTO dto) {
    ExchangeDTO saved = new ExchangeDTO(" ", " ",0.0);
    saved.setFirstCurrency(dto.getFirstCurrency());
    saved.setSecondCurrency(dto.getSecondCurrency());
    saved.setExchangeCourse(dto.getExchangeCourse());
    return saved;
  }
}
