package com.speMajor.demo.service.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BmiCalculator.class})
@ExtendWith(SpringExtension.class)
class BmiCalculatorTest {
    @Autowired
    private BmiCalculator bmiCalculator;

    /**
     * Method under test: {@link BmiCalculator#calculateBmi(double, double)}
     */
    @Test
    void testCalculateBmi() {
        assertEquals(0.1d, bmiCalculator.calculateBmi(10.0d, 10.0d));
        assertEquals(-1.0d, bmiCalculator.calculateBmi(0.0d, 10.0d));
        assertEquals(10.0d, bmiCalculator.calculateBmi(1.0d, 10.0d));
        assertEquals(40.0d, bmiCalculator.calculateBmi(0.5d, 10.0d));
        assertEquals(-1.0d, bmiCalculator.calculateBmi(10.0d, 0.0d));
    }

    /**
     * Method under test: {@link BmiCalculator#calculate(double, double)}
     */
    @Test
    void testCalculate() {
        assertEquals("Underweight. Your BMI is 0.1", bmiCalculator.calculate(10.0d, 10.0d));
        assertEquals("Obese. Your BMI is 999.9999999999998", bmiCalculator.calculate(0.1d, 10.0d));
        assertEquals("Invalid Inputs", bmiCalculator.calculate(0.0d, 10.0d));
        assertEquals("Invalid Inputs", bmiCalculator.calculate(10.0d, 0.0d));
        assertEquals("Healthy. Your BMI is 18.5", bmiCalculator.calculate(1.0d, 18.5d));
        assertEquals("Overweight. Your BMI is 25.0", bmiCalculator.calculate(1.0d, 25.0d));
    }
}

