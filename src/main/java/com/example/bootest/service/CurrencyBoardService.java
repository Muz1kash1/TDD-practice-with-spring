package com.example.bootest.service;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.repository.CurrencyBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyBoardService {
  private final CurrencyBoardDao currencyBoardDao;

  @Autowired
  public CurrencyBoardService(CurrencyBoardDao currencyBoardDao) {
    this.currencyBoardDao = currencyBoardDao;
  }

  public String getBoard() {
    String[] boardView = {""};
    currencyBoardDao
        .getBoard()
        .forEach(
            (key, value) -> {
              boardView[0] += (key.toString() + " " + value.toString() + "\n");
            });

    return boardView[0];
  }

  public String getCoursesForCurrency(Currency currency) {
    String[] currencyCourses = {""};
    currencyBoardDao.getExchangeCoursesOf(currency).forEach((key, value) -> {
        currencyCourses[0] += (key.toString() + " " + value.toString() + "\n");
    });
    return currencyCourses[0];
  }
}
