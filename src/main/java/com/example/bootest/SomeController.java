package com.example.bootest;

import com.example.bootest.domailn.model.Currency;
import com.example.bootest.domailn.model.DTOs.ExchangeDTO;
import com.example.bootest.service.CurrencyBoardService;
import com.example.bootest.service.ExchangeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@Slf4j
@RestController("/some")
public class SomeController {
  @Autowired private final CurrencyBoardService currencyBoardService;

  @GetMapping("/board")
  public ResponseEntity getCoursesBoard() {
    //    return new ResponseEntity<>(currencyBoardService.getBoard(), HttpStatus.OK);
    return ResponseEntity.ok().body(currencyBoardService.getBoard());
  }

  @GetMapping("/board/{currencyName}")
  public ResponseEntity getCoursesForCurrency(@PathVariable String currencyName) {
    return ResponseEntity.ok().body(currencyBoardService.getCoursesForCurrency(currencyName));
  }

  @PostMapping(
      value = "/board",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ExchangeDTO> CreateCourseInBoard(@RequestBody ExchangeDTO dto) {
    ExchangeDTO exchangeDTO = ExchangeMapper.save(dto);
    currencyBoardService.inserCurrencyInBank(
        Currency.valueOf(dto.getFirstCurrency()),
        Currency.valueOf(dto.getSecondCurrency()),
        dto.getExchangeCourse());
    return ResponseEntity.created(
            URI.create(
                String.format("/courses/%s", dto.getFirstCurrency() + dto.getSecondCurrency())))
        .body(exchangeDTO);
  }

  @GetMapping("/exchange")
  public ResponseEntity<Double> exchange(
      @RequestParam String firstCurrencyName,
      @RequestParam String firstCurrencyAmount,
      @RequestParam String secondCurrencyName) {
    return new ResponseEntity<>(
        currencyBoardService.getCourseBetweenTwoValutes(
            firstCurrencyName, Double.parseDouble(firstCurrencyAmount), secondCurrencyName),
        HttpStatus.OK);
  }
//TODO: Сделать так чтобы он не выкидывал екзеп
  @DeleteMapping(
      value = "/board",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public void deleteExchangePair(@RequestBody ExchangeDTO dto) {
    ExchangeDTO exchangeDTO = ExchangeMapper.save(dto);
    currencyBoardService.deleteCurrencyFromBank(
        Currency.valueOf(dto.getFirstCurrency()), Currency.valueOf(dto.getSecondCurrency()));

  }

  @ExceptionHandler(RuntimeException.class)
  public String handleException(RuntimeException e) {
    log.error(e.getMessage());
    return "vse ploha";
  }
}
