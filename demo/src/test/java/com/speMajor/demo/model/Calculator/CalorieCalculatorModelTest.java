package com.speMajor.demo.model.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CalorieCalculatorModel.class})
@ExtendWith(SpringExtension.class)
class CalorieCalculatorModelTest {
    @Autowired
    private CalorieCalculatorModel calorieCalculatorModel;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalorieCalculatorModel#CalorieCalculatorModel()}
     *   <li>{@link CalorieCalculatorModel#setActivityLevel(int)}
     *   <li>{@link CalorieCalculatorModel#setAge(int)}
     *   <li>{@link CalorieCalculatorModel#setGender(char)}
     *   <li>{@link CalorieCalculatorModel#setHeight(double)}
     *   <li>{@link CalorieCalculatorModel#setWeight(double)}
     *   <li>{@link CalorieCalculatorModel#getActivityLevel()}
     *   <li>{@link CalorieCalculatorModel#getAge()}
     *   <li>{@link CalorieCalculatorModel#getGender()}
     *   <li>{@link CalorieCalculatorModel#getHeight()}
     *   <li>{@link CalorieCalculatorModel#getWeight()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CalorieCalculatorModel actualCalorieCalculatorModel = new CalorieCalculatorModel();
        actualCalorieCalculatorModel.setActivityLevel(1);
        actualCalorieCalculatorModel.setAge(1);
        actualCalorieCalculatorModel.setGender('A');
        actualCalorieCalculatorModel.setHeight(10.0d);
        actualCalorieCalculatorModel.setWeight(10.0d);
        int actualActivityLevel = actualCalorieCalculatorModel.getActivityLevel();
        int actualAge = actualCalorieCalculatorModel.getAge();
        char actualGender = actualCalorieCalculatorModel.getGender();
        double actualHeight = actualCalorieCalculatorModel.getHeight();
        assertEquals(1, actualActivityLevel);
        assertEquals(1, actualAge);
        assertEquals('A', actualGender);
        assertEquals(10.0d, actualHeight);
        assertEquals(10.0d, actualCalorieCalculatorModel.getWeight());
    }

    /**
     * Method under test: {@link CalorieCalculatorModel#CalorieCalculatorModel(int, char, double, double, int)}
     */
    @Test
    void testConstructor2() {
        CalorieCalculatorModel actualCalorieCalculatorModel = new CalorieCalculatorModel(1, 'A', 10.0d, 10.0d, 1);

        assertEquals(1, actualCalorieCalculatorModel.getActivityLevel());
        assertEquals(10.0d, actualCalorieCalculatorModel.getWeight());
        assertEquals(10.0d, actualCalorieCalculatorModel.getHeight());
        assertEquals('A', actualCalorieCalculatorModel.getGender());
        assertEquals(1, actualCalorieCalculatorModel.getAge());
    }
}

