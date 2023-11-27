package com.speMajor.demo.service.Department;

import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO addDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO updateDepartmentDetails(DepartmentDTO departmentDTO,Long id);
    DepartmentDTO getDepartmentById(Long id);
    List<DepartmentDTO> getAllDepartments();
    void deleteDepartment(Long id);

}
