package com.speMajor.demo.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.model.Calculator.BmiCalculatorModel;
import com.speMajor.demo.service.Other.SalaryService;
import com.speMajor.demo.service.calculator.BmiCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CalculatorController.class})
@ExtendWith(SpringExtension.class)
class CalculatorControllerDiffblueTest {
  @MockBean
  private BmiCalculator bmiCalculator;

  @Autowired
  private CalculatorController calculatorController;

  @MockBean
  private SalaryService salaryService;

  /**
   * Method under test:  {@link CalculatorController#calculateBmi(BmiCalculatorModel)}
   */
  @Test
  void testCalculateBmi() throws Exception {
    when(bmiCalculator.calculate(anyDouble(), anyDouble())).thenReturn("Calculate");

    BmiCalculatorModel bmiCalculatorModel = new BmiCalculatorModel();
    bmiCalculatorModel.setHeight(10.0d);
    bmiCalculatorModel.setWeight(10.0d);
    String content = (new ObjectMapper()).writeValueAsString(bmiCalculatorModel);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/calculator/bmi")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    MockMvcBuilders.standaloneSetup(calculatorController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
            .andExpect(MockMvcResultMatchers.content().string("Calculate"));
  }

  /**
   * Method under test:  {@link CalculatorController#calculateSalary(Long)}
   */
  @Test
  void testCalculateSalary() throws Exception {
    when(salaryService.calculate(Mockito.<Long>any())).thenReturn(10.0d);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/calculator/salary");
    MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
    MockMvcBuilders.standaloneSetup(calculatorController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("10.0"));
  }

  @Test
  void testCalculateBaseSalary() throws Exception {
    when(salaryService.findBaseSalary(anyLong())).thenReturn(10.0d);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/calculator/baseSalary");
    MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
    MockMvcBuilders.standaloneSetup(calculatorController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("10.0"));
  }

  /**
   * Method under test:  {@link CalculatorController#calculateTax(Long)}
   */
  @Test
  void testCalculateTax() throws Exception {
    when(salaryService.calculateTax(Mockito.<Long>any())).thenReturn(10.0d);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/calculator/tax");
    MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
    MockMvcBuilders.standaloneSetup(calculatorController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("10.0"));
  }
}
