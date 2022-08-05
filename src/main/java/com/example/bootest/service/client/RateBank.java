package com.example.bootest.service.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class RateBank {
  @JsonProperty("Valute")
  private Map<String, Valute> valute;
}
