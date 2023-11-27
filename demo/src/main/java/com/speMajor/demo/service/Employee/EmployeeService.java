package com.speMajor.demo.service.Employee;

import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.payload.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO registerNewUser(EmployeeDTO employeeDTO,Long departmentId);
    EmployeeDTO addEmployee(EmployeeDTO EmployeeDTO,Long departmentId);
    EmployeeDTO updateEmployeeDetails(EmployeeDTO EmployeeDTO,Long id);
    EmployeeDTO getEmployeeById(Long id);
    EmployeeResponse getAllEmployee(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    void deleteEmployee(Long id);

    List<EmployeeDTO> getEmployeeByDepartment(Long departmentId);
    List<EmployeeDTO> serachEmployee(String name);
}
