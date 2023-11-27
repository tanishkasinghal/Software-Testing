package com.speMajor.demo.service.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BmrCalculator.class})
@ExtendWith(SpringExtension.class)
class BmrCalculatorTest {
    @Autowired
    private BmrCalculator bmrCalculator;

    /**
     * Method under test: {@link BmrCalculator#calculateBmr(int, char, double, double)}
     */
    @Test
    void testCalculateBmr() {
        assertEquals(-1.0d, BmrCalculator.calculateBmr(1, 'A', 10.0d, 10.0d));
        assertEquals(-1.0d, BmrCalculator.calculateBmr(15, 'A', 10.0d, 10.0d));
        assertEquals(92.5d, BmrCalculator.calculateBmr(15, 'M', 10.0d, 10.0d));
        assertEquals(92.5d, BmrCalculator.calculateBmr(15, 'm', 10.0d, 10.0d));
        assertEquals(-73.5d, BmrCalculator.calculateBmr(15, 'F', 10.0d, 10.0d));
        assertEquals(-73.5d, BmrCalculator.calculateBmr(15, 'f', 10.0d, 10.0d));
        assertEquals(-1.0d, BmrCalculator.calculateBmr(15, 'A', -1.0d, 10.0d));
        assertEquals(-1.0d, BmrCalculator.calculateBmr(15, 'A', 10.0d, -1.0d));
        assertEquals(-1.0d, BmrCalculator.calculateBmr(109, 'm', 10.0d, 10.0d));
    }

    /**
     * Method under test: {@link BmrCalculator#calculate(int, char, double, double)}
     */
    @Test
    void testCalculate() {
        assertEquals("Invalid Inputs", bmrCalculator.calculate(1, 'A', 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bmrCalculator.calculate(15, 'A', 10.0d, 10.0d));
        assertEquals("BMR = 92.5 calories/day", bmrCalculator.calculate(15, 'M', 10.0d, 10.0d));
        assertEquals("BMR = 92.5 calories/day", bmrCalculator.calculate(15, 'm', 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bmrCalculator.calculate(15, 'F', 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bmrCalculator.calculate(15, 'f', 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bmrCalculator.calculate(15, 'A', -1.0d, 10.0d));
        assertEquals("Invalid Inputs", bmrCalculator.calculate(15, 'A', 10.0d, -1.0d));
        assertEquals("Invalid Inputs", bmrCalculator.calculate(109, 'm', 10.0d, 10.0d));
    }
}

