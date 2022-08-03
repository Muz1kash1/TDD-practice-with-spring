package com.example.bootest.domailn.model;

import lombok.Data;

@Data
public class ExchangePair {

  private final Currency firstCurrency;
  private final Currency secondCurrency;

  public ExchangePair(Currency firstCurrency, Currency secondCurrency) {
    this.firstCurrency = firstCurrency;
    this.secondCurrency = secondCurrency;
  }
}
