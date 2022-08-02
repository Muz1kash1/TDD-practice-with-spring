package com.example.bootest.domailn.model.repository;

import com.example.bootest.domailn.model.CurrencyBoard;
import com.example.bootest.domailn.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyBoardDao {
    @Autowired
    private static CurrencyBoard currencyBoard;

    public static double getExchangeForPair(Wallet.ExchangePair pair){
        return currencyBoard.getCurrencyBoard().get(pair);
    }
    public static Map<Wallet.ExchangePair,Double> getBoard(){
        return currencyBoard.getCurrencyBoard();
    }
}
