package com.example.bootest.repository;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.ExchangePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyBoardDao {
//  private final CurrencyBoard currencyBoard;
//
//  @Autowired
//  public CurrencyBoardDao(CurrencyBoard currencyBoard) {
//    this.currencyBoard = currencyBoard;
//  }
//
//  public double getExchangeCourseForPair(ExchangePair pair) {
//    return currencyBoard.getCurrencyBoard().get(pair);
//  }
//
//  public Map<ExchangePair, Double> getBoard() {
//    return currencyBoard.getCurrencyBoard();
//  }

//  public Map<String, Double> getExchangeCoursesOf(String currency) {
//    Map<String, Double> exchangesForThisCurrency = new HashMap<>();
//    for (Map.Entry<String, Double> entry : currencyBoard.getCoursesFromCentralBank().entrySet()) {
//      exchangesForThisCurrency.put(
//          entry.getKey(),
//          entry.getValue() / currencyBoard.getCoursesFromCentralBank().get(currency));
//    }
//
//    return exchangesForThisCurrency;
//  }

//  public Currency getCurrencyByName(String name) {
//    return Currency.valueOf(name);
//  }

//  public Double getCourseByValutes(String firstValute, String secondValute) {
//    return currencyBoard.getCoursesFromCentralBank().get(firstValute)
//        / currencyBoard.getCoursesFromCentralBank().get(secondValute);
//  }

//  public Map.Entry<ExchangePair, Double> createCourencyPairInCB(
//      Currency firstCurrency, Currency secondCurrency, Double exchangeCourse) {
//    return currencyBoard.insertCurrencyExchangePair(firstCurrency, secondCurrency, exchangeCourse);
//  }
//
//  public void deleteCurrencyPairFromCB(Currency firstCurrency, Currency secondCurrency) {
//    currencyBoard.deleteCurrencyExchaingePair(firstCurrency, secondCurrency);
//  }
}
