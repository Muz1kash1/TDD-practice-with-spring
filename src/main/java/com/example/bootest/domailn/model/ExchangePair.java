package com.example.bootest.domailn.model;

public class ExchangePair {

  private final Currency firstCurrency;
  private final Currency secondCurrency;

  public ExchangePair(Currency firstCurrency, Currency secondCurrency) {
    this.firstCurrency = firstCurrency;
    this.secondCurrency = secondCurrency;
  }

  public Currency getFirstCurrency() {
    return this.firstCurrency;
  }

  public Currency getSecondCurrency() {
    return this.secondCurrency;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof ExchangePair)) return false;
    final ExchangePair other = (ExchangePair) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$firstCurrency = this.getFirstCurrency();
    final Object other$firstCurrency = other.getFirstCurrency();
    if (this$firstCurrency == null ? other$firstCurrency != null : !this$firstCurrency.equals(other$firstCurrency))
      return false;
    final Object this$secondCurrency = this.getSecondCurrency();
    final Object other$secondCurrency = other.getSecondCurrency();
    if (this$secondCurrency == null ? other$secondCurrency != null : !this$secondCurrency.equals(other$secondCurrency))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ExchangePair;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $firstCurrency = this.getFirstCurrency();
    result = result * PRIME + ($firstCurrency == null ? 43 : $firstCurrency.hashCode());
    final Object $secondCurrency = this.getSecondCurrency();
    result = result * PRIME + ($secondCurrency == null ? 43 : $secondCurrency.hashCode());
    return result;
  }

  public String toString() {
    return this.getFirstCurrency() + "-" + this.getSecondCurrency();
  }
}
