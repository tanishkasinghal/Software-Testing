package com.speMajor.demo.controller;
import com.speMajor.demo.model.Calculator.*;
import com.speMajor.demo.service.Other.SalaryService;

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
    SalaryService salaryService;
    @GetMapping("/salary")
    public ResponseEntity<?> calculateSalary(@RequestParam Long id){
        return ResponseEntity.ok(this.salaryService.calculate(id));
    }

    @GetMapping("/tax")
    public ResponseEntity<?> calculateTax(@RequestParam Long id){
        return ResponseEntity.ok(this.salaryService.calculateTax(id));
    }

    @GetMapping("/baseSalary")
    public ResponseEntity<?> calculateBaseSalary(@RequestParam Long id){
        return ResponseEntity.ok(this.salaryService.findBaseSalary(id));
    }


    @GetMapping("/bmi")
    public ResponseEntity<?> calculateBmi(@NotNull @RequestBody BmiCalculatorModel bmiCalculatorModel){
        return ResponseEntity.ok(this.bmi.calculate(bmiCalculatorModel.getHeight(),bmiCalculatorModel.getWeight()));
    }

}
