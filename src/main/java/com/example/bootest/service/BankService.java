package com.example.bootest.service;

import com.example.bootest.domailn.model.DTOs.ExchangeDTO;
import com.example.bootest.repository.RatesRepository;
import com.example.bootest.service.client.Valute;
import org.springframework.stereotype.Service;

@Service
public class BankService {

  private final RatesRepository repository;

  public BankService(RatesRepository repository) {
    this.repository = repository;
  }

  /**
   * Получит объект с данными о требуемой конвертации в виде объекта и вернет требуемое значение в виде числа
   * @param exchangeDTO объект содержащий валютную пару которую надо сконвертировать и число конвертируемой валюты
   * @return возвращает количество второй валюты полученное после конверсии первой
   */

  public double convert(ExchangeDTO exchangeDTO) {
    Valute valuteFrom = repository.getRateBank().getValute().get(exchangeDTO.getFirstCurrency());

    Valute valuteTo = repository.getRateBank().getValute().get(exchangeDTO.getSecondCurrency());

    return valuteFrom.getValue()
        / valuteFrom.getNominal()
        / valuteTo.getValue()
        / valuteTo.getNominal()
        * exchangeDTO.getAmount();
  }
}
