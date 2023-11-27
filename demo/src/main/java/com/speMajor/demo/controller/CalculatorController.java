package com.speMajor.demo.controller;
import com.speMajor.demo.model.Calculator.*;
import com.speMajor.demo.service.SalaryCalculator;
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

    @Autowired
    SalaryCalculator salaryCalculator;
    @GetMapping("/salary")
    public ResponseEntity<?> calculateSalary(@RequestParam Long id){
        return ResponseEntity.ok(this.salaryCalculator.calculate(id));
    }

    @GetMapping("/tax")
    public ResponseEntity<?> calculateTax(@RequestParam Long id){
        return ResponseEntity.ok(this.salaryCalculator.calculateTax(id));
    }

    @GetMapping("/baseSalary")
    public ResponseEntity<?> calculateBaseSalary(@RequestParam Long id){
        return ResponseEntity.ok(this.salaryCalculator.findBaseSalary(id));
    }


    @GetMapping("/bmi")
    public ResponseEntity<?> calculateBmi(@NotNull @RequestBody BmiCalculatorModel bmiCalculatorModel){
        return ResponseEntity.ok(this.bmi.calculate(bmiCalculatorModel.getHeight(),bmiCalculatorModel.getWeight()));
    }

}
