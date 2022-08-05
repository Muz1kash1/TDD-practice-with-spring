package com.example.bootest.service.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Service
public class CentralBankService {

  private final RestTemplate restTemplate;
  private final String url = "https://www.cbr-xml-daily.ru/daily_json.js";

  /**
   * создаст службу центробанка содержащую в resttemplate мапер джексона в http запрос поддерживающий все медиатипы
   * @param restTemplateBuilder
   */
  public CentralBankService(RestTemplateBuilder restTemplateBuilder) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
    this.restTemplate =
        restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(5000))
            .setReadTimeout(Duration.ofMillis(5000))
            .messageConverters(converter)
            .build();
  }

  /**
   * вызовет гет метод по ссылке на внешний источник в виде объекта банка
   * @return ответ содержащий в теле банк
   */

  private ResponseEntity<RateBank> callClient() {
    return restTemplate.exchange(url, HttpMethod.GET, null, RateBank.class);
  }

  /**
   * Вернет банк из клиента и вызовет проверку на непустоту его тела
   * @return вернет банк из клиента
   */
  public RateBank getRateBank() {

    final ResponseEntity<RateBank> response = callClient();
    checkResponse(response);
    return response.getBody();
  }

  /**
   * Проверит тело ответа на непустоту
   * @param response проверяемый ответ
   */
  private void checkResponse(final ResponseEntity<RateBank> response) {

    if (!response.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException(response.getStatusCode().getReasonPhrase());
    }

    if (response.getBody() == null) {
      throw new RuntimeException("empty body");
    }
  }
}
