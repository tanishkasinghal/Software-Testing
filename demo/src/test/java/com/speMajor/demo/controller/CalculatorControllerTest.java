package com.speMajor.demo.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.speMajor.demo.service.SalaryCalculator;
import com.speMajor.demo.service.calculator.BmiCalculator;
import com.speMajor.demo.service.calculator.BmrCalculator;
import com.speMajor.demo.service.calculator.BodyFatCalculator;
import com.speMajor.demo.service.calculator.CalorieCalculator;
import com.speMajor.demo.service.calculator.IdealWeightCalculator;
import com.speMajor.demo.service.calculator.LeanBodyMassCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CalculatorController.class})
@ExtendWith(SpringExtension.class)
class CalculatorControllerTest {
    @MockBean
    private BmiCalculator bmiCalculator;

    @MockBean
    private BmrCalculator bmrCalculator;

    @MockBean
    private BodyFatCalculator bodyFatCalculator;

    @Autowired
    private CalculatorController calculatorController;

    @MockBean
    private CalorieCalculator calorieCalculator;

    @MockBean
    private IdealWeightCalculator idealWeightCalculator;

    @MockBean
    private LeanBodyMassCalculator leanBodyMassCalculator;

    @MockBean
    private SalaryCalculator salaryCalculator;

    /**
     * Method under test: {@link CalculatorController#calculateBaseSalary(Long)}
     */
    @Test
    void testCalculateBaseSalary() throws Exception {
        when(salaryCalculator.findBaseSalary(anyLong())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/calculator/baseSalary");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(calculatorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }
}

