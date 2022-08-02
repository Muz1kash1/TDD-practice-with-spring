package com.example.bootest.domailn.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Wallet {
  @Data
  public static class ExchangePair{

    private final Currency firstCurrency;
    private final Currency secondCurrency;

    public ExchangePair(Currency firstCurrency, Currency secondCurrency) {
      this.firstCurrency = firstCurrency;
      this.secondCurrency = secondCurrency;
    }

  }
  @Autowired private final List<Money> moneyList;

  public Wallet(Money... money) {
    moneyList = List.of(money);
  }

  @Override
  public String toString() {
    return "Wallet{" + "moneyList=" + moneyList + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Wallet wallet = (Wallet) o;

    return moneyList.equals(wallet.moneyList);
  }

  /**
   * Возвращает новый кошелёк с стопками денег которые уже были внутри и с новой стопкой денег
   *
   * @param money
   * @return
   */
  public Wallet plus(Money money) {
    List<Money> newlist = new ArrayList<>();
    newlist.addAll(moneyList);
    newlist.add(money);
    Money[] args = newlist.toArray(new Money[0]);
    return new Wallet(args);
  }
  /**
   * Переводит денежный объект в денежный объект с указанным типом валюты по текущим курсам из
   * currencyBoard
   */
  public Money asCurrency(Map<ExchangePair, Double> currencyBoard, Currency currency) {
    double amount = 0.0;

    for (Money money : moneyList) {

      if (money.currency == currency) {
        amount += money.amount;

        continue;
      }
      for (Map.Entry<ExchangePair, Double> entry : currencyBoard.entrySet()) {

        if (entry.getKey().getFirstCurrency() == (money.currency)
            && entry.getKey().getSecondCurrency() == (currency)) {

          amount += money.amount * entry.getValue();

          continue;
        }
      }
    }
    switch (currency) {
      case USD:
        return Money.dollar(amount);
      case CHF:
        return Money.franc(amount);
      case EUR:
        return Money.euro(amount);
      default:
        return Money.dollar(amount);
    }
  }

  @Override
  public int hashCode() {
    return moneyList.hashCode();
  }
}
