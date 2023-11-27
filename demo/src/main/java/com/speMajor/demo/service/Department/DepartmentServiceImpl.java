package com.speMajor.demo.service.Department;


import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.repository.DepartmentRepository;
import com.speMajor.demo.repository.EmployeeRepository;
import com.speMajor.demo.service.Department.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department=this.dtoToDepartment(departmentDTO);
        Department savedDepartment=this.departmentRepository.save(department);
        return this.DepartmentTodto(savedDepartment);
    }

    @Override
    public DepartmentDTO updateDepartmentDetails(DepartmentDTO departmentDTO, Long id) {
        Department department=this.departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("dept","id",id));
        department.setDeptName(departmentDTO.getDeptName());
        Department updatedDepartment=this.departmentRepository.save(department);
        return this.DepartmentTodto(updatedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department=this.departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("dept","id",id));
        return this.DepartmentTodto(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments=this.departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS=departments.stream().map(department -> this.DepartmentTodto(department)).collect(Collectors.toList());
        return departmentDTOS;
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department=this.departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("dept","id",id));
        departmentRepository.delete(department);
    }

    public Department dtoToDepartment(DepartmentDTO departmentDTO){
        Department department = this.modelMapper.map(departmentDTO, Department.class);
        return  department;
    }

    public DepartmentDTO DepartmentTodto(Department department){
        DepartmentDTO departmentDTO=this.modelMapper.map(department,DepartmentDTO.class);
        return departmentDTO;
    }
}
