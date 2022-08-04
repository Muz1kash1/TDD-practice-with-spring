package com.example.bootest;

import com.example.bootest.domailn.model.CentralBankConnectionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootestApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(BootestApplication.class, args);
    CentralBankConnectionReader.read();
  }
}
