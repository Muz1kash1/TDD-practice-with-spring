package com.example.bootest.repository;

import com.example.bootest.service.client.CentralBankService;
import com.example.bootest.service.client.RateBank;
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
}
