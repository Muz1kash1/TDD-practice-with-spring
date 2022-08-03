package com.example.bootest.repository;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.ExchangePair;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class CurrencyBoard {
  private final Map<ExchangePair, Double> currencyBoard =
      Map.of(
          new ExchangePair(Currency.USD, Currency.EUR),
          0.5,
          new ExchangePair(Currency.CHF, Currency.EUR),
          2.0,
          new ExchangePair(Currency.EUR, Currency.CHF),
          0.5);

  private final Map<String, Currency> currencyNames =
      Map.of(
          "Euro", Currency.EUR,
          "Franc", Currency.CHF,
          "Dollar", Currency.USD);
}
