package com.example.bootest;

import com.example.bootest.domailn.model.DTOs.ExchangeDTO;
import com.example.bootest.domailn.model.DTOs.ExchangeDTOMapper;
import com.example.bootest.repository.RatesRepository;
import com.example.bootest.service.BankService;
import com.example.bootest.service.client.Valute;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@Slf4j
@RestController("/some")
public class SomeController {
  //  @Autowired private final CurrencyBoardService currencyBoardService;
  @Autowired private final BankService bankService;
  @Autowired private final RatesRepository repository;

  //  @GetMapping("/board")
  //  public ResponseEntity getCoursesBoard() {
  //    //    return new ResponseEntity<>(currencyBoardService.getBoard(), HttpStatus.OK);
  //    return ResponseEntity.ok().body(currencyBoardService.getBoard());
  //  }

  //  @GetMapping("/board/{currencyName}")
  //  public ResponseEntity getCoursesForCurrency(@PathVariable String currencyName) {
  //    return ResponseEntity.ok().body(currencyBoardService.getCoursesForCurrency(currencyName));
  //  }

  //  @PostMapping(
  //      value = "/board",
  //      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
  //      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  //  public ResponseEntity<ExchangeDTO> CreateCourseInBoard(@RequestBody ExchangeDTO dto) {
  //    ExchangeDTO exchangeDTO = ExchangeDTOMapper.save(dto);
  //    currencyBoardService.inserCurrencyInBank(
  //        Currency.valueOf(dto.getFirstCurrency()),
  //        Currency.valueOf(dto.getSecondCurrency()),
  //        dto.getExchangeCourse());
  //    return ResponseEntity.created(
  //            URI.create(
  //                String.format("/courses/%s", dto.getFirstCurrency() + dto.getSecondCurrency())))
  //        .body(exchangeDTO);
  //  }

  /**
   * Конвертирует полученное количество полученной первой валюты в полученную вторую валюту
   *
   * @param firstCurrencyName наименование конвертируемой валюты
   * @param firstCurrencyAmount количество конвертируемой валюты
   * @param secondCurrencyName наименование валюты в которую произойдет конвертация
   * @return вернет численное значение количества сконвертированной валюты
   */
  @GetMapping("/exchange")
  public ResponseEntity<Double> exchange(
      @RequestParam String firstCurrencyName,
      @RequestParam String firstCurrencyAmount,
      @RequestParam String secondCurrencyName) {
    ExchangeDTO exchangeDTO =
        ExchangeDTOMapper.save(
            firstCurrencyName, secondCurrencyName, Double.parseDouble(firstCurrencyAmount));
    return ResponseEntity.ok().body(bankService.convert(exchangeDTO));
  }

  @PostMapping("/addnew")
  public ResponseEntity<Valute> putNewValute(
      @RequestParam String charcode, @RequestParam String amount, @RequestParam String value) {
    repository.putValute(charcode, Double.parseDouble(amount), Double.parseDouble(value));
    return ResponseEntity.created(URI.create(String.format("/courses/%s", charcode)))
        .body(repository.getRateBank().getValute().get(charcode));
  }

  //  @DeleteMapping(
  //      value = "/board",
  //      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
  //      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
  //  public void deleteExchangePair(@RequestBody ExchangeDTO dto) {
  //    currencyBoardService.deleteCurrencyFromBank(
  //        Currency.valueOf(dto.getFirstCurrency()), Currency.valueOf(dto.getSecondCurrency()));
  //  }

  @ExceptionHandler(RuntimeException.class)
  public String handleException(RuntimeException e) {
    log.error(e.getMessage());
    return "vse ploha";
  }
}
