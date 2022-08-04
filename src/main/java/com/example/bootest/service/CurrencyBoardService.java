package com.example.bootest.service;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.ExchangePair;
import com.example.bootest.domailn.model.Money;
import com.example.bootest.domailn.model.Wallet;
import com.example.bootest.repository.CurrencyBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyBoardService {
  private final CurrencyBoardDao currencyBoardDao;

  @Autowired
  public CurrencyBoardService(CurrencyBoardDao currencyBoardDao) {
    this.currencyBoardDao = currencyBoardDao;
  }

  public Wallet currencyAddition(
      String firstCurrencyName,
      double firstCurrencyAmount,
      String secondCurrencyName,
      double secondCurrencyAmount) {
    return Money.customCurrency(firstCurrencyAmount, getCourencyByName(firstCurrencyName))
        .plus(Money.customCurrency(secondCurrencyAmount, getCourencyByName(secondCurrencyName)));
  }

  //  public String getBoard() {
  //    String[] boardView = {""};
  //    currencyBoardDao
  //        .getBoard()
  //        .forEach(
  //            (key, value) -> {
  //              boardView[0] += (key.toString() + " " + value.toString() + "\n");
  //            });
  //
  //    return boardView[0];
  //  }
  public Map<ExchangePair, Double> getBoard() {
    return currencyBoardDao.getBoard();
  }

  //  public String getCoursesForCurrency(Currency currency) {
  //    String[] currencyCourses = {""};
  //    currencyBoardDao
  //        .getExchangeCoursesOf(currency)
  //        .forEach(
  //            (key, value) -> {
  //              currencyCourses[0] += (key.toString() + " " + value.toString() + "\n");
  //            });
  //    return currencyCourses[0];
  //  }
  public Map<String, Double> getCoursesForCurrency(String currency) {
    Map<String, Double> coursesForCurrency =
        new HashMap<>(currencyBoardDao.getExchangeCoursesOf(currency));
    return coursesForCurrency;
  }

  public Double getCourseBetweenTwoValutes(
      String firstValute, Double firstCurrencyAmount, String secondValute) {
    return firstCurrencyAmount * currencyBoardDao.getCourseByValutes(firstValute, secondValute);
  }

  public Currency getCourencyByName(String name) {
    return currencyBoardDao.getCurrencyByName(name);
  }

  public Map.Entry<ExchangePair, Double> inserCurrencyInBank(
      Currency firstCurrency, Currency secondCurrency, Double exchangeCourse) {
    return currencyBoardDao.createCourencyPairInCB(firstCurrency, secondCurrency, exchangeCourse);
  }

  public void deleteCurrencyFromBank(Currency firstCurrency, Currency secondCurrency) {
    currencyBoardDao.deleteCurrencyPairFromCB(firstCurrency, secondCurrency);
  }
}
