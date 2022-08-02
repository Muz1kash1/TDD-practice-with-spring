package tddtest;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.Money;
import com.example.bootest.domailn.model.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
  private final Map<Wallet.ExchangePair, Double> currencyBoard = new HashMap<>();

  @DisplayName("dollar should Not Return Null")
  @Test
  public void dollar_shouldNotReturnNull() {
    assertThat(Money.dollar(1)).isNotNull();
  }

  @DisplayName("franc should Not Return Null")
  @Test
  public void franc_shouldNotReturnNull() {
    assertThat(Money.franc(1)).isNotNull();
  }

  @DisplayName("euro should Not Return Null")
  @Test
  public void euro_shouldNotReturnNull() {
    assertThat(Money.euro(1)).isNotNull();
  }

  @DisplayName("equals 1 Cfh should Be equal 1 Chf")
  @Test
  public void equals_1Cfh_shouldBe_equal_1Chf() {
    assertThat(Money.franc(1)).isEqualTo(Money.franc(1));
  }

  @DisplayName("equals 1 Chf should Be Not Equal 1 Usd")
  @Test
  public void equals_1Chf_shouldBeNotEqual_1Usd() {
    assertThat(Money.franc(1)).isNotEqualTo(Money.dollar(1));
  }

  @DisplayName("equals 1 eur should Be equal 1 eur")
  @Test
  public void equals_1eur_shouldBe_equal_1eur() {
    assertThat(Money.euro(1)).isEqualTo(Money.euro(1));
  }

  @DisplayName("equals 1 eur should Be Not Equal 1 Usd")
  @Test
  public void equals_1eur_shouldBeNotEqual_1Usd() {
    assertThat(Money.euro(1)).isNotEqualTo(Money.dollar(1));
  }

  @DisplayName("equals 1 Chf should Be not Equal 2 Chf")
  @Test
  public void equals_1Chf_shouldBe_notEqual_2Chf() {
    assertThat(Money.franc(1)).isNotEqualTo(Money.franc(2));
  }

  @DisplayName("equals 1 Eur should Be not Equal 2 Eur")
  @Test
  public void equals_1Eur_shouldBe_notEqual_2Eur() {
    assertThat(Money.euro(1)).isNotEqualTo(Money.euro(2));
  }

  @DisplayName("equals 1 Usd should Be equal 1 Usd")
  @Test
  public void equals_1Usd_shouldBe_equal_1Usd() {
    assertThat(Money.dollar(1)).isEqualTo(Money.dollar(1));
  }

  @DisplayName("equals 1 Usd should Be Not Equal 1 Chf")
  @Test
  public void equals_1Usd_shouldBeNotEqual_1Chf() {
    assertThat(Money.dollar(1)).isNotEqualTo(Money.franc(1));
  }

  @DisplayName("equals 1 Usd should Be not Equal 2 Usd")
  @Test
  public void equals_1Usd_shouldBe_notEqual_2Usd() {
    assertThat(Money.dollar(1)).isNotEqualTo(Money.dollar(2));
  }

  @DisplayName("multiply 2 Chf Times 2 should Be 4 Chf")
  @Test
  public void multiply_2ChfTimes2_shouldBe_4Chf() {
    assertThat(Money.franc(2).times(2)).isEqualTo(Money.franc(4));
  }

  @DisplayName("multiply 2 Eur Times 2 should Be 4 Eur")
  @Test
  public void multiply_2EurTimes2_shouldBe_4Eur() {
    assertThat(Money.euro(2).times(2)).isEqualTo(Money.euro(4));
  }

  @DisplayName("multiply 3 Eur Times 2 should Be 6 Eur")
  @Test
  public void multiply_3EurTimes2_shouldBe_6Eur() {
    assertThat(Money.euro(3).times(2)).isEqualTo(Money.euro(6));
  }

  @DisplayName("multiply 3 Chf Times 2 should Be 6 Chf")
  @Test
  public void multiply_3ChfTimes2_shouldBe_6Chf() {
    assertThat(Money.franc(3).times(2)).isEqualTo(Money.franc(6));
  }

  @DisplayName("multiply 2 Usd Times 2 should Be 4 Usd")
  @Test
  public void multiply_2UsdTimes2_shouldBe_4Usd() {
    assertThat(Money.dollar(2).times(2)).isEqualTo(Money.dollar(4));
  }

  @DisplayName("multiply 3 Usd Times 2 shouldBe 6 Usd")
  @Test
  public void multiply_3UsdTimes2_shouldBe_6Usd() {
    assertThat(Money.dollar(3).times(2)).isEqualTo(Money.dollar(6));
  }

  @DisplayName("plus 2 Chf Plus 4 Usd And Rate 2 to 1 should Be 8 Usd")
  @Test
  public void plus_2ChfPlus4UsdAndRate2to1_shouldBe_8Usd() {
    currencyBoard.put(new Wallet.ExchangePair(Currency.EUR, Currency.USD), 1.2);
    currencyBoard.put(new Wallet.ExchangePair(Currency.CHF, Currency.USD), 2.0);
    assertThat(Money.franc(2.0).plus(Money.dollar(4.0)).asCurrency(currencyBoard, Currency.USD))
        .isEqualTo(Money.dollar(8));
  }
}
