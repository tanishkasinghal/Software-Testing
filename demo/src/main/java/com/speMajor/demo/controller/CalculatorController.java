package com.speMajor.demo.controller;
import com.speMajor.demo.model.Calculator.*;
import com.speMajor.demo.service.calculator.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    @Autowired
    BmiCalculator bmi=new BmiCalculator();
    @Autowired
    BmrCalculator bmr=new BmrCalculator();
    @Autowired
    BodyFatCalculator bodyFat=new BodyFatCalculator();
    @Autowired
    CalorieCalculator calorie=new CalorieCalculator();
    @Autowired
    IdealWeightCalculator idealWeight=new IdealWeightCalculator();
    @Autowired
    LeanBodyMassCalculator leanBodyMass=new LeanBodyMassCalculator();

    @GetMapping("/bmi")
    public ResponseEntity<?> calculateBmi(@NotNull @RequestBody BmiCalculatorModel bmiCalculatorModel){
        return ResponseEntity.ok(this.bmi.calculate(bmiCalculatorModel.getHeight(),bmiCalculatorModel.getWeight()));
    }

    @GetMapping("/bmr")
    public ResponseEntity<?> calculateBmr(@NotNull @RequestBody BmrCalculatorModel bmrCalculatorModel){
        return ResponseEntity.ok(this.bmr.calculate(bmrCalculatorModel.getAge(), bmrCalculatorModel.getGender(),bmrCalculatorModel.getHeight(),bmrCalculatorModel.getWeight()));
    }

    @GetMapping("/bodyFat")
    public ResponseEntity<?> calculateBodyFat(@NotNull @RequestBody BodyFatCalculatorModel bodyFatCalculatorModel){
        return ResponseEntity.ok(this.bodyFat.calculate(bodyFatCalculatorModel.getGender(),bodyFatCalculatorModel.getWeight(),
                bodyFatCalculatorModel.getHeight(), bodyFatCalculatorModel.getNeck(), bodyFatCalculatorModel.getWaist(), bodyFatCalculatorModel.getHip()));
    }

    @GetMapping("/calorie")
    public ResponseEntity<?> calculateCalorie(@NotNull @RequestBody CalorieCalculatorModel calorieCalculatorModel){
        return ResponseEntity.ok(this.calorie.calculate(calorieCalculatorModel.getAge(),calorieCalculatorModel.getGender(),
                calorieCalculatorModel.getHeight(),calorieCalculatorModel.getWeight(),calorieCalculatorModel.getActivityLevel()));
    }

    @GetMapping("/idealWeight")
    public ResponseEntity<?> calculateIdealWeight(@NotNull @RequestBody IdealWeightCalculatorModel idealWeightCalculatorModel){
        return ResponseEntity.ok(this.idealWeight.calculate(idealWeightCalculatorModel.getAge(),idealWeightCalculatorModel.getGender(),idealWeightCalculatorModel.getHeight()));
    }

    @GetMapping("/leanBodyMass")
    public ResponseEntity<?> calculateLeanBodyMass(@NotNull @RequestBody LeanBodyMassCalculatorModel leanBodyMassCalculatorModel){
        return ResponseEntity.ok(this.leanBodyMass.calculate(leanBodyMassCalculatorModel.getYounger(),leanBodyMassCalculatorModel.getGender(),leanBodyMassCalculatorModel.getHeight(),
                leanBodyMassCalculatorModel.getWeight()));
    }
}
