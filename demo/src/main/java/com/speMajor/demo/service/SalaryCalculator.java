package com.speMajor.demo.service;


import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryCalculator {

    @Autowired
    EmployeeRepository employeeRepository;
    public double calculateSalary(Employee employee, String department) {
        long level=employee.getLevel();

        double baseSalary = findBaseSalary(level);
        double bonus;

        if ("HR".equals(department) ) {
            bonus = baseSalary * 0.1;
        } else if ("IT".equals(department) || "DEV".equals(department)) {
            bonus = baseSalary * 0.15;
        } else {
            bonus = baseSalary * 0.05;
        }

        return baseSalary + bonus;
    }
    public double findBaseSalary(long level)
    {

        if (level >= 0 && level < 5) {
            return 50000; // Base salary for levels 1-4
        } else if (level >= 5 && level < 10) {
            return 60000; // Base salary for levels 5-9
        } else if (level >= 10 && level < 15) {
            return 70000; // Base salary for levels 10-14
        } else if (level >= 15 && level <= 18.5) {
            return 80000; // Base salary for levels 15-18.5
        } else {
            throw new IllegalArgumentException("Invalid designation level");
        }

    }

    public double calculate(Long id){
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","email: "+id, 0L));

        String department=employee.getDepartment().getDeptName();

        SalaryCalculator salaryCalculator=new SalaryCalculator();
        double res=salaryCalculator.calculateSalary(employee,department);
        return res;
    }

    public double calculateTax(Long id) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","email: "+id, 0L));
        double baseSalary = findBaseSalary(employee.getLevel());
        double tax;

        if (baseSalary <= 50000) {
            // 10% tax for salary up to $50,000
            tax = 0.1 * baseSalary;
        } else if (baseSalary <= 100000) {
            // 15% tax for salary between $50,001 and $100,000
            tax = 0.15 * baseSalary;
        } else {
            // 20% tax for salary above $100,000
            tax = 0.2 * baseSalary;
        }

        return tax;
    }
}
