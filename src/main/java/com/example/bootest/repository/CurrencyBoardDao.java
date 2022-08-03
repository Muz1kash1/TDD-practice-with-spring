package com.example.bootest.repository;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.ExchangePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyBoardDao {
  private final CurrencyBoard currencyBoard;

  @Autowired
  public CurrencyBoardDao(CurrencyBoard currencyBoard) {
    this.currencyBoard = currencyBoard;
  }

  public double getExchangeCourseForPair(ExchangePair pair) {
    return currencyBoard.getCurrencyBoard().get(pair);
  }

  public Map<ExchangePair, Double> getBoard() {
    return currencyBoard.getCurrencyBoard();
  }

  public Map<ExchangePair, Double> getExchangeCoursesOf(Currency currency) {
    Map<ExchangePair, Double> exchangesForThisCurrency = new HashMap<>();
    for (Map.Entry<ExchangePair, Double> entry : currencyBoard.getCurrencyBoard().entrySet()) {
      if (entry.getKey().getFirstCurrency().equals(currency)) {
        exchangesForThisCurrency.put(entry.getKey(), entry.getValue());
      }
    }
    return exchangesForThisCurrency;
  }

  public Currency getCurrencyByName(String name) {
    return currencyBoard.getCurrencyNames().get(name);
  }
}
