package com.example.bootest.dataacess;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Data
@Repository
public class BalanceRepository {
  private final HashMap<Long, Long> balances = new HashMap<>(Map.of(1L, 20L));
}
