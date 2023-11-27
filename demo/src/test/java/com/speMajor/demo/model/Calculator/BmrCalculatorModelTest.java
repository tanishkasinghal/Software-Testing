package com.speMajor.demo.model.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BmrCalculatorModel.class})
@ExtendWith(SpringExtension.class)
class BmrCalculatorModelTest {
    @Autowired
    private BmrCalculatorModel bmrCalculatorModel;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BmrCalculatorModel#BmrCalculatorModel()}
     *   <li>{@link BmrCalculatorModel#setAge(int)}
     *   <li>{@link BmrCalculatorModel#setGender(char)}
     *   <li>{@link BmrCalculatorModel#setHeight(double)}
     *   <li>{@link BmrCalculatorModel#setWeight(double)}
     *   <li>{@link BmrCalculatorModel#getAge()}
     *   <li>{@link BmrCalculatorModel#getGender()}
     *   <li>{@link BmrCalculatorModel#getHeight()}
     *   <li>{@link BmrCalculatorModel#getWeight()}
     * </ul>
     */
    @Test
    void testConstructor() {
        BmrCalculatorModel actualBmrCalculatorModel = new BmrCalculatorModel();
        actualBmrCalculatorModel.setAge(1);
        actualBmrCalculatorModel.setGender('A');
        actualBmrCalculatorModel.setHeight(10.0d);
        actualBmrCalculatorModel.setWeight(10.0d);
        int actualAge = actualBmrCalculatorModel.getAge();
        char actualGender = actualBmrCalculatorModel.getGender();
        double actualHeight = actualBmrCalculatorModel.getHeight();
        assertEquals(1, actualAge);
        assertEquals('A', actualGender);
        assertEquals(10.0d, actualHeight);
        assertEquals(10.0d, actualBmrCalculatorModel.getWeight());
    }

    /**
     * Method under test: {@link BmrCalculatorModel#BmrCalculatorModel(int, char, double, double)}
     */
    @Test
    void testConstructor2() {
        BmrCalculatorModel actualBmrCalculatorModel = new BmrCalculatorModel(1, 'A', 10.0d, 10.0d);

        assertEquals(1, actualBmrCalculatorModel.getAge());
        assertEquals(10.0d, actualBmrCalculatorModel.getWeight());
        assertEquals(10.0d, actualBmrCalculatorModel.getHeight());
        assertEquals('A', actualBmrCalculatorModel.getGender());
    }
}

