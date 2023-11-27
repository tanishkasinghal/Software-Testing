package com.speMajor.demo.controller;
import com.speMajor.demo.config.AppConstants;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.payload.ApiResponse;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.payload.EmployeeResponse;
import com.speMajor.demo.service.Employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/department/{deptId}/add")
   public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,@PathVariable Long deptId){
       EmployeeDTO newEmployeeDTO=this.employeeService.addEmployee(employeeDTO,deptId);
       return new ResponseEntity<>(newEmployeeDTO, HttpStatus.CREATED);
   } //working

    @GetMapping("/department/{deptId}/get")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartmentId(@PathVariable Long deptId){
        List<EmployeeDTO> employeeDTOS=this.employeeService.getEmployeeByDepartment(deptId);
        return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS,HttpStatus.OK);
    }
   @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,@PathVariable Long id){
        EmployeeDTO updatedEmployeeDTO=this.employeeService.updateEmployeeDetails(employeeDTO,id);
        return ResponseEntity.ok(updatedEmployeeDTO);
   }
   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("id") Long id){
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
   } //working



   @GetMapping("/")
   public ResponseEntity<EmployeeResponse> getAllEmployees(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                           @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                           @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
       EmployeeResponse employeeResponse=this.employeeService.getAllEmployee(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<EmployeeResponse>(employeeResponse,HttpStatus.OK);
   } //working

   @GetMapping("/{id}")
   public ResponseEntity<EmployeeDTO> getEmployeebyId(@PathVariable Long id){
        EmployeeDTO employeeDTO=this.employeeService.getEmployeeById(id);
        return new ResponseEntity<EmployeeDTO>(employeeDTO,HttpStatus.OK);
   }

   @GetMapping("/search/{keyword}")
   public ResponseEntity<List<EmployeeDTO>> searchEmployeeByName(
           @PathVariable("keyword") String keyword){
    List<EmployeeDTO> employeeDTOS=this.employeeService.serachEmployee(keyword);
    return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS,HttpStatus.OK);
   }
}
