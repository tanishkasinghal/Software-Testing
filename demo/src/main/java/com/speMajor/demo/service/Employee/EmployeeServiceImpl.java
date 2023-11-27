package com.speMajor.demo.service.Employee;

import com.speMajor.demo.config.AppConstants;
import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.model.Role;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.payload.EmployeeResponse;
import com.speMajor.demo.repository.DepartmentRepository;
import com.speMajor.demo.repository.EmployeeRepository;
import com.speMajor.demo.repository.RoleRepository;
import com.speMajor.demo.service.Employee.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public EmployeeDTO registerNewUser(EmployeeDTO employeeDTO,Long departmentId) {
        Department department=this.departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("department","dept_id",departmentId));
        Employee employee=this.modelMapper.map(employeeDTO,Employee.class);
        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));
        employee.setJoiningDate(new Date());
        employee.setDepartment(department);
        //roles
        Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        employee.getRoles().add(role);
        Employee newEmployee=this.employeeRepository.save(employee);
        return this.modelMapper.map(newEmployee,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO EmployeeDTO,Long departmentId) {
        Department department=this.departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("department","dept_id",departmentId));
        Employee employee=this.modelMapper.map(EmployeeDTO,Employee.class);
        employee.setJoiningDate(new Date());
        employee.setDepartment(department);
        Employee savedEmployee=this.employeeRepository.save(employee);
        return this.modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeDetails(EmployeeDTO EmployeeDTO, Long id) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee","id",id));
        employee.setFirstName(EmployeeDTO.getFirstName());
        employee.setLastName(EmployeeDTO.getLastName());
        employee.setEmailId(EmployeeDTO.getEmailId());
        employee.setPassword(EmployeeDTO.getPassword());
        Employee updatedEmployee=this.employeeRepository.save(employee);
        return this.modelMapper.map(updatedEmployee,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee","empid",id));
        return this.modelMapper.map(employee,EmployeeDTO.class);
    }

    @Override
    public EmployeeResponse getAllEmployee(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize, sort); //Sort.by(sortBy).descending or ascendng
        Page<Employee> pageEmployees=this.employeeRepository.findAll(p);
        List<Employee> allEmployee=pageEmployees.getContent();
        List<EmployeeDTO> employeeDTOS=allEmployee.stream().map((employee)->this.modelMapper.map(employee,EmployeeDTO.class)).collect(Collectors.toList());
        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setContent(employeeDTOS);
        employeeResponse.setPageNumber(pageEmployees.getNumber());
        employeeResponse.setPageSize(pageEmployees.getSize());
        employeeResponse.setTotalElements(pageEmployees.getTotalElements());
        employeeResponse.setTotalPages(pageEmployees.getTotalPages());
        employeeResponse.setLastPage(pageEmployees.isLast());
        return employeeResponse;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee","empid",id));
        this.employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByDepartment(Long departmentId) {
        Department department=this.departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("department","dept)id",departmentId));
        List<Employee> employees= this.employeeRepository.findByDepartment(department);
        List<EmployeeDTO> employeeDTOs=employees.stream().map((employee) -> this.modelMapper.map(employee,EmployeeDTO.class)).collect(Collectors.toList());
        return employeeDTOs;
    }

    @Override
    public List<EmployeeDTO> serachEmployee(String name) {
        //List<Employee> employees=this.employeeRepository.searchbyFirstName("%"+key+"%");
        List<Employee> employees=this.employeeRepository.findByFirstNameContaining(name);
        List<EmployeeDTO> employeeDTOS=employees.stream().map((employee)-> this.modelMapper.map(employee,EmployeeDTO.class)).collect(Collectors.toList());
        return employeeDTOS;
    }

    public Employee dtoToEmployee(EmployeeDTO employeeDTO){
        Employee employee = this.modelMapper.map(employeeDTO, Employee.class);

//        Employee employee=new Employee();
//        employee.setId(employeeDTO.getId());
//        employee.setFirstName(employeeDTO.getFirstName());
//        employee.setLastName(employeeDTO.getLastName());
//        employee.setEmailId(employeeDTO.getEmailId());
//        employee.setPassword(employeeDTO.getPassword());
        return  employee;
    }

    public EmployeeDTO EmployeeTodto(Employee employee){
        EmployeeDTO employeeDTO=this.modelMapper.map(employee,EmployeeDTO.class);
//        EmployeeDTO employeeDTO=new EmployeeDTO();
//        employeeDTO.setId(employee.getId());
//        employeeDTO.setFirstName(employee.getFirstName());
//        employeeDTO.setLastName(employee.getLastName());
//        employeeDTO.setEmailId(employee.getEmailId());
//        employeeDTO.setPassword(employee.getPassword());
        return employeeDTO;
    }
}
