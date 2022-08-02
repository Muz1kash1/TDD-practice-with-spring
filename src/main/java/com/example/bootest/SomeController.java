package com.example.bootest;

import com.example.bootest.domailn.model.Money;
import com.example.bootest.domailn.model.NumberIncrementer;
import com.example.bootest.service.NumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController("/some")
public class SomeController {
  private final NumberService service;

  @GetMapping("/{id}")
  public long getNumber(@PathVariable long id) {
    return service.getBanalceFromId(id);
  }

  @PostMapping("/{id}")
  public long add(@RequestBody NumberIncrementer dto) {
    return service.addToBalanceById(dto.getTo(), dto.getAmount());
  }

  @PostMapping("/transfer")
  public long transferNum(@RequestBody NumberIncrementer dto) {
    return service.transferNum(dto.getFrom(),dto.getTo(),dto.getAmount());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String handleException(IllegalArgumentException e){
    log.error(e.getMessage());
    return "vse ploha";
  }
  
  
}
