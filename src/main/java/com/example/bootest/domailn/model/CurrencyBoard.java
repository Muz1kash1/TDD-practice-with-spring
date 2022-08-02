package com.example.bootest.domailn.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Data
@Component
public class CurrencyBoard {
    private final Map<Wallet.ExchangePair, Double> currencyBoard = new HashMap<>();
}
