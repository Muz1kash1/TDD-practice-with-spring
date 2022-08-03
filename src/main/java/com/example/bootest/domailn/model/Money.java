package com.example.bootest.domailn.model;

public class Money {
  protected final Currency currency;

  protected double amount;

  protected Money(double amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static Money customCurrency(double amount, Currency customCurrency){
    Money custom = new Money(amount,customCurrency);
    return custom;
  }

  /**
   * возвращает новый доллар
   *
   * @param amount
   * @return
   */
  public static Money dollar(double amount) {
    Money dollar = new Money(amount, Currency.USD);
    return dollar;
  }

  /**
   * возвращает новый франк
   *
   * @param amount
   * @return
   */
  public static Money franc(double amount) {
    Money franc = new Money(amount, Currency.CHF);
    return franc;
  }

  /**
   * возвращает новый евро
   *
   * @param amount
   * @return
   */
  public static Money euro(double amount) {
    Money euro = new Money(amount, Currency.EUR);
    return euro;
  }
  /**
   * производит умножение денег
   *
   * @param multiplier
   * @return
   */
  public Money times(int multiplier) {
    Money multiplied = new Money(this.amount * multiplier, this.currency);
    return multiplied;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Money)) return false;

    Money money = (Money) o;

    if (amount != money.amount) return false;
    return currency == money.currency;
  }

  @Override
  public int hashCode() {
    int result = (int) amount;
    result = 31 * result + (currency != null ? currency.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Money{" + "amount=" + amount + ", currency=" + currency + '}';
  }

  /**
   * Возвращает новый кошелёк с этой стопкой денег и с данной стопкой денег
   *
   * @param money
   * @return
   */
  public Wallet plus(Money money) {
    Wallet wallet = new Wallet(this, money);
    return wallet;
  }
  //  public Money plus(Money money){
  //
  //    if (this.currency == money.currency){
  //      return new Money(this.amount + money.amount,this.currency)
  //    }
  //    else throw new RuntimeException("Сложение разных типов денег");
  //  }

}
