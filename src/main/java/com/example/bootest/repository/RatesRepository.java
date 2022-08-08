package com.example.bootest.repository;

import com.example.bootest.service.client.CentralBankService;
import com.example.bootest.service.client.RateBank;
import com.example.bootest.service.client.Valute;
import org.springframework.stereotype.Repository;

@Repository
public class RatesRepository {
  private final RateBank rateBank;

  public RatesRepository(CentralBankService centralBankService) {
    rateBank = centralBankService.getRateBank();
  }

  public RateBank getRateBank() {
    return rateBank;
  }

  public Valute putValute(String charcode,double amount,double value){
    Valute valute = new Valute();
    valute.setCharCode(charcode);
    valute.setNominal(amount);
    valute.setValue(value);
    rateBank.getValute().put(charcode,valute);
    return valute;
  }

}
