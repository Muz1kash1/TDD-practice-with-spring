package com.example.bootest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootestApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(BootestApplication.class, args);
    CentralBankXMLParser.read();
  }
}
