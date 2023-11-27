package com.speMajor.demo.controller;


import com.speMajor.demo.payload.ApiResponse;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.service.Department.DepartmentService;
import com.speMajor.demo.service.Employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO newDepartmentDTO=this.departmentService.addDepartment(departmentDTO);
        return new ResponseEntity<>(newDepartmentDTO, HttpStatus.CREATED);
    } //working

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO,@PathVariable Long id){
        DepartmentDTO updatedDepartmentDTO=this.departmentService.updateDepartmentDetails(departmentDTO,id);
        return ResponseEntity.ok(updatedDepartmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable("id") Long id){
        this.departmentService.deleteDepartment(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Dept Deleted Successfully",true),HttpStatus.OK);
    } //working

    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(this.departmentService.getAllDepartments());
    } //working

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentbyId(@PathVariable Long id){
        return ResponseEntity.ok(this.departmentService.getDepartmentById(id));
    } // working
}
