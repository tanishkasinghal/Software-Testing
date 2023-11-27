package com.speMajor.demo.service.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BodyFatCalculator.class})
@ExtendWith(SpringExtension.class)
class BodyFatCalculatorTest {
    @Autowired
    private BodyFatCalculator bodyFatCalculator;

    /**
     * Method under test: {@link BodyFatCalculator#calculate(char, double, double, double, double, double)}
     */
    @Test
    void testCalculate() {
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('A', 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('F', 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('f', 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('M', 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('m', 10.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('F', -1.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('F', 10.0d, -1.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('F', 10.0d, 10.0d, -1.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('F', 10.0d, 10.0d, 495.0d, 10.0d, 10.0d));
        assertEquals("Obese. Fat percentage: 72.43051925367803",
                bodyFatCalculator.calculate('F', 10.0d, 10.0d, 1.0324d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('F', 10.0d, 10.0d, 10.0d, -1.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('M', -1.0d, 10.0d, 10.0d, 10.0d, 10.0d));
        assertEquals("Invalid Inputs", bodyFatCalculator.calculate('M', 10.0d, 10.0d, 495.0d, 10.0d, 10.0d));
        assertEquals("Obese. Fat percentage: 72.43051925367803",
                bodyFatCalculator.calculate('M', 10.0d, 10.0d, 1.0324d, 10.0d, 10.0d));
    }
}

