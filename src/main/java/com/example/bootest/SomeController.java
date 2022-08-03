package com.example.bootest;

import com.example.bootest.service.CurrencyBoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController("/some")
public class SomeController {
  @Autowired private final CurrencyBoardService currencyBoardService;

  //  @PostMapping("/transfer")
  //  public long transferNum(@RequestBody NumberIncrementer dto) {
  //    return service.transferNum(dto.getFrom(),dto.getTo(),dto.getAmount());
  //  }

  @GetMapping("/board")
  public String printBoard() {
    return currencyBoardService.getBoard();
  }

  @GetMapping("/board/{currencyName}")
  public String printForCurrency(@PathVariable String currencyName) {
    return currencyBoardService.getCoursesForCurrency(
        currencyBoardService.getCourencyByName(currencyName));
  }

  @GetMapping("/operation/addition")
  public String addition(
      @RequestParam String firstCurrencyName,
      @RequestParam String firstCurrencyAmount,
      @RequestParam String secondCurrencyName,
      @RequestParam String secondCurrencyAmount) {
    return currencyBoardService.currencyAddition(
        firstCurrencyName,
        Double.parseDouble(firstCurrencyAmount),
        secondCurrencyName,
        Double.parseDouble(secondCurrencyAmount));
  }

  @ExceptionHandler(RuntimeException.class)
  public String handleException(RuntimeException e) {
    log.error(e.getMessage());
    return "vse ploha";
  }
}
