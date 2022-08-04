package com.example.bootest.repository;

import com.example.bootest.domailn.model.CentralBankConnectionReader;
import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.ExchangePair;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Data
@Component
public class CurrencyBoard {
  private final Map<String, Double> coursesFromCentralBank = CentralBankConnectionReader.read();
  private final Map<ExchangePair, Double> currencyBoard = new HashMap<>();
  //      Map.of(
  //          new ExchangePair(Currency.USD, Currency.EUR),
  //          coursesFromCentralBank.get("USD") / coursesFromCentralBank.get("EUR"),
  //          new ExchangePair(Currency.CHF, Currency.EUR),
  //          coursesFromCentralBank.get("CHF") / coursesFromCentralBank.get("EUR"),
  //          new ExchangePair(Currency.EUR, Currency.CHF),
  //          coursesFromCentralBank.get("EUR") / coursesFromCentralBank.get("CHF"));

  public CurrencyBoard() throws Exception {
    for (Map.Entry<String, Double> entry : coursesFromCentralBank.entrySet()) {
      for (Map.Entry<String, Double> entry1 : coursesFromCentralBank.entrySet()) {
        currencyBoard.put(
            new ExchangePair(Currency.valueOf(entry.getKey()), Currency.valueOf(entry1.getKey())),
            entry.getValue() / entry1.getValue());
      }
    }
  }

  public Map<ExchangePair, Double> getCurrencyBoard() {
    return this.currencyBoard;
  }

  public Map<String, Double> getCoursesFromCentralBank() {
    return coursesFromCentralBank;
  }

  public Map.Entry<ExchangePair, Double> insertCurrencyExchangePair(
      Currency firstCurrency, Currency secondCurrency, Double exchangeCourse) {
    ExchangePair pairKey = new ExchangePair(firstCurrency, secondCurrency);
    AtomicReference<Map.Entry<ExchangePair, Double>> inserted = new AtomicReference<>();
    currencyBoard.put(pairKey, exchangeCourse);
    currencyBoard
        .entrySet()
        .forEach(
            (entry) -> {
              if (entry.getKey().equals(pairKey)) {
                inserted.set(entry);
              }
            });

    return inserted.get();
  }

  public void deleteCurrencyExchaingePair(Currency firstCurrency, Currency secondCurrency) {

    ExchangePair pairToDelete = new ExchangePair(firstCurrency, secondCurrency);
    // Не осуждайте стажера за костыль
    try {
      currencyBoard
          .entrySet()
          .forEach(
              (entry -> {
                System.out.println(entry.getKey());
                if (entry.getKey().equals(pairToDelete)) {
                  currencyBoard.remove(pairToDelete);
                }
              }));
    } catch (RuntimeException e) {
      return;
    }
  }
}
