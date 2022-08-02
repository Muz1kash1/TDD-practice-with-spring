package com.example.bootest.service;

import com.example.bootest.dataacess.BalanceRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
public class NumberService {
  @Autowired private final BalanceRepository repository;

  public long getBanalceFromId(long id) throws IllegalArgumentException {
    if (repository.getBalances().get(id) == null) {
      throw new IllegalArgumentException();
    }
    return repository.getBalances().get(id);
  }

  public long addToBalanceById(long id, long amount) {
    repository.getBalances().put(id, repository.getBalances().get(id) + amount);
    return repository.getBalances().get(id);
  }

  public long transferNum(long from, long to, long amount) {
    repository.getBalances().put(from, repository.getBalances().get(from) - amount);
    return repository.getBalances().put(to, repository.getBalances().get(to) + amount);
  }
}
