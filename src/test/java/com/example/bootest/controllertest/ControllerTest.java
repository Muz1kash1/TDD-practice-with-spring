package com.example.bootest.controllertest;


import com.example.bootest.domailn.model.DTOs.ExchangeDTO;
import com.example.bootest.service.BankService;
import com.example.bootest.service.client.CentralBankService;
import com.example.bootest.service.client.RateBank;
import com.example.bootest.service.client.Valute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes= BankService.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class ControllerTest {


}
