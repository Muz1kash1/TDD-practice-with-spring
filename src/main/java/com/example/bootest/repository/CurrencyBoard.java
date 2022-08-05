package com.example.bootest.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CurrencyBoard {
//  private final Map<ExchangePair, Double> currencyBoard = new HashMap<>();
//  private Map<String, Double> coursesFromCentralBank = CentralBankParser.read();

//  public CurrencyBoard() throws Exception {
//    for (Map.Entry<String, Double> entry : coursesFromCentralBank.entrySet()) {
//      for (Map.Entry<String, Double> entry1 : coursesFromCentralBank.entrySet()) {
//        currencyBoard.put(
//            new ExchangePair(Currency.valueOf(entry.getKey()), Currency.valueOf(entry1.getKey())),
//            entry.getValue() / entry1.getValue());
//      }
//    }
//  }

//  public Map<ExchangePair, Double> getCurrencyBoard() {
//    return this.currencyBoard;
//  }

//  public Map<String, Double> getCoursesFromCentralBank() {
//    return coursesFromCentralBank;
//  }

//  public Map.Entry<ExchangePair, Double> insertCurrencyExchangePair(
//      Currency firstCurrency, Currency secondCurrency, Double exchangeCourse) {
//    ExchangePair pairKey = new ExchangePair(firstCurrency, secondCurrency);
//    AtomicReference<Map.Entry<ExchangePair, Double>> inserted = new AtomicReference<>();
//    currencyBoard.put(pairKey,exchangeCourse);
//    currencyBoard
//        .entrySet()
//        .forEach(
//            (entry) -> {
//              if (entry.getKey().equals(pairKey)) {
//                inserted.set(entry);
//              }
//            });
//
//    return inserted.get();
//  }

//  public void deleteCurrencyExchaingePair(Currency firstCurrency, Currency secondCurrency) {
//
//    ExchangePair pairToDelete = new ExchangePair(firstCurrency, secondCurrency);
//    currencyBoard.remove(pairToDelete);
//  }
}
