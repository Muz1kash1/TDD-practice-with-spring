package com.example.bootest.domailn.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeDTO {
  private String firstCurrency;
  private String secondCurrency;
  private Double amount;
}
