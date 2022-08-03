package tddtest;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.ExchangePair;
import com.example.bootest.domailn.model.Money;
import com.example.bootest.domailn.model.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WalletTest {
  private Map<ExchangePair, Double> currencyBoard = new HashMap<>();

  @DisplayName("wallet with 1 Dollar should Be Equal Wallet With 1 Dollar")
  @Test
  public void wallet_with1Dollar_shouldBeEqualWalletWith1Dollar() {
    assertThat(new Wallet(Money.dollar(1))).isEqualTo(new Wallet(Money.dollar(1)));
  }

  @DisplayName("wallet with 1 Dollar should Not Be Equal Wallet With 2 Dollar")
  @Test
  public void wallet_with1Dollar_shouldNotBeEqualWalletWith2Dollar() {
    assertThat(new Wallet(Money.dollar(1))).isNotEqualTo(new Wallet(Money.dollar(2)));
  }

  @DisplayName("wallet with 1 Euro should Be Equal Wallet With 1 Euro")
  @Test
  public void wallet_with1Euro_shouldBeEqualWalletWith1Euro() {
    assertThat(new Wallet(Money.euro(1))).isEqualTo(new Wallet(Money.euro(1)));
  }

  @DisplayName("wallet with 1 Euro should Not Be Equal Wallet With 2 Euro")
  @Test
  public void wallet_with1Euro_shouldNotBeEqualWalletWith2Euro() {
    assertThat(new Wallet(Money.euro(1))).isNotEqualTo(new Wallet(Money.euro(2)));
  }

  @DisplayName("wallet with 1 Dollar And 1 Chf should Be Equal Wallet With 1 dollar And 1 Chf")
  @Test
  public void wallet_with1DollarAnd1Chf_shouldBeEqualWalletWith1dollarAnd1Chf() {
    assertThat(new Wallet(Money.dollar(1), Money.franc(1)))
        .isEqualTo(new Wallet(Money.dollar(1), Money.franc(1)));
  }

  @DisplayName(
      "wallet with 1 Dollar plus wallet with 1 Chf should Be Equal Wallet With 1 Dollar And 1 Chf")
  @Test
  public void wallet_with1Dollar_plus_wallet_with1Chf_shouldBeEqualWalletWith1DollarAnd1Chf() {
    assertThat(Money.dollar(1).plus(Money.franc(1)))
        .isEqualTo(new Wallet(Money.dollar(1), Money.franc(1)));
  }

  @DisplayName(
      "Wallet with 1 Euro plus wallet with 1CHF should not be equal wallet with 1 usd and 1chf")
  @Test
  public void wallet_with1Euro_plus_wallet_with1Chf_shoulNotdBeEqualWalletWith1DollarAnd1Chf() {
    assertThat(Money.euro(1).plus(Money.franc(1)))
        .isNotEqualTo(new Wallet(Money.dollar(1), Money.franc(1)));
  }

  @DisplayName("Wallet with 2 Euro And 3 Chf and 4 USD as Currency USD should Be Equal 20 USD")
  @Test
  public void Wallet_with2EuroAnd3ChfAnd4Dollar_asCurrencyUSD_shouldBeEqual20Dollar() {
    currencyBoard.put(new ExchangePair(Currency.EUR, Currency.USD), 2.0);
    currencyBoard.put(new ExchangePair(Currency.CHF, Currency.USD), 4.0);

    assertThat(
            Money.euro(2.0)
                .plus(Money.franc(3.0))
                .plus(Money.dollar(4))
                .asCurrency(currencyBoard, Currency.USD))
        .isEqualTo(Money.dollar(20));
  }

  @DisplayName("Wallet with 3 Euro And 5Chf as Currency EUR should Not Be Equal 20 Euro")
  @Test
  public void Wallet_with3EuroAnd5ChfAnd4Dollar_asCurrencyEUR_shouldNotBeEqual20Euro() {
    currencyBoard.put(new ExchangePair(Currency.USD, Currency.EUR), 0.5);
    currencyBoard.put(new ExchangePair(Currency.CHF, Currency.EUR), 2.0);

    assertThat(
            Money.euro(3.0)
                .plus(Money.franc(5.0))
                .plus(Money.dollar(4))
                .asCurrency(currencyBoard, Currency.EUR))
        .isNotEqualTo(Money.dollar(20));
  }

  @DisplayName("Wallet with 3 Euro And 5Chf should Be Equal Wallet with 3Euro And 1Chf Plus 4Chf")
  @Test
  public void Wallet_with3EuroAnd5Chf_shouldBeEqual_Wallet_with3EuroAnd1ChfPlus4Chf() {
    currencyBoard.put(new ExchangePair(Currency.USD, Currency.EUR), 0.5);
    currencyBoard.put(new ExchangePair(Currency.CHF, Currency.EUR), 2.0);
    assertThat(
        (new Wallet(Money.euro(3), Money.franc(5))
            .equals(
                new Wallet(
                    Money.euro(3),
                    Money.franc(1).plus(Money.franc(4)).asCurrency(currencyBoard, Currency.CHF)))));
  }
}
