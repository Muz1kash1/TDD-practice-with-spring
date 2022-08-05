package com.example.bootest.service.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Valute {

  @JsonProperty("CharCode")
  private String charCode;

  @JsonProperty("Nominal")
  private int nominal;

  @JsonProperty("Value")
  private double value;
}
