package com.speMajor.demo.model.Calculator;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyFatCalculatorModel {
    char gender;
    double weight;
    double height;
    double neck;
    double waist;
    double hip;
}
