package com.speMajor.demo.service.Employee;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.model.Role;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.payload.EmployeeResponse;
import com.speMajor.demo.repository.DepartmentRepository;
import com.speMajor.demo.repository.EmployeeRepository;
import com.speMajor.demo.repository.RoleRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplDiffblueTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#registerNewUser(EmployeeDTO, Long)}
     */
    @Test
    void testRegisterNewUser() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Employee>>any())).thenReturn(employee);

        Role role = new Role();
        role.setId(1);
        role.setName("Name");
        Optional<Role> ofResult2 = Optional.of(role);
        when(roleRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.registerNewUser(new EmployeeDTO(), 1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());

    }

//    /**
//     * Method under test: {@link EmployeeServiceImpl#addEmployee(EmployeeDTO, Long)}
//     */
//    @Test
//    void testAddEmployee() {
//        Department department = new Department();
//        department.setDeptName("Dept Name");
//        department.setEmployeeList(new ArrayList<>());
//        department.setId(1L);
//        Optional<Department> ofResult = Optional.of(department);
//        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
//        when(employeeRepository.save(Mockito.<Employee>any()))
//                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
//
//        Department department2 = new Department();
//        department2.setDeptName("Dept Name");
//        department2.setEmployeeList(new ArrayList<>());
//        department2.setId(1L);
//
//        Employee employee = new Employee();
//        employee.setDepartment(department2);
//        employee.setEmailId("42");
//        employee.setFirstName("Jane");
//        employee.setId(1L);
//        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
//        employee.setLastName("Doe");
//        employee.setLevel(1L);
//        employee.setPassword("iloveyou");
//        employee.setRoles(new HashSet<>());
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Employee>>any())).thenReturn(employee);
//        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.addEmployee(new EmployeeDTO(), 1L));
//        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
//        verify(departmentRepository).findById(Mockito.<Long>any());
//        verify(employeeRepository).save(Mockito.<Employee>any());
//        assertNotNull(employee);
//        assertEquals("encodedPassword", employee.getPassword());
//        assertNotNull(employee.getJoiningDate());
//        assertEquals(department, employee.getDepartment());
//    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#updateEmployeeDetails(EmployeeDTO, Long)}
     */
    @Test
    void testUpdateEmployeeDetails() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setEmailId("42");
        employee2.setFirstName("Jane");
        employee2.setId(1L);
        employee2.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee2.setLastName("Doe");
        employee2.setLevel(1L);
        employee2.setPassword("iloveyou");
        employee2.setRoles(new HashSet<>());
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(employeeDTO);
        EmployeeDTO actualUpdateEmployeeDetailsResult = employeeServiceImpl.updateEmployeeDetails(new EmployeeDTO(), 1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
        assertSame(employeeDTO, actualUpdateEmployeeDetailsResult);
    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#updateEmployeeDetails(EmployeeDTO, Long)}
     */
    @Test
    void testUpdateEmployeeDetails2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setEmailId("42");
        employee2.setFirstName("Jane");
        employee2.setId(1L);
        employee2.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee2.setLastName("Doe");
        employee2.setLevel(1L);
        employee2.setPassword("iloveyou");
        employee2.setRoles(new HashSet<>());
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class,
                () -> employeeServiceImpl.updateEmployeeDetails(new EmployeeDTO(), 1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

//    /**
//     * Method under test:
//     * {@link EmployeeServiceImpl#updateEmployeeDetails(EmployeeDTO, Long)}
//     */
//    @Test
//    void testUpdateEmployeeDetails3() {
//        Optional<Employee> emptyResult = Optional.empty();
//        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
//        assertThrows(ResourceNotFoundException.class,
//                () -> employeeServiceImpl.updateEmployeeDetails(new EmployeeDTO(), 1L));
//        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
//        verify(employeeRepository).findById(Mockito.<Long>any());
//    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(employeeDTO);
        EmployeeDTO actualEmployeeById = employeeServiceImpl.getEmployeeById(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
        assertSame(employeeDTO, actualEmployeeById);
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

//    /**
//     * Method under test: {@link EmployeeServiceImpl#getEmployeeById(Long)}
//     */
//    @Test
//    void testGetEmployeeById3() {
//        Optional<Employee> emptyResult = Optional.empty();
//        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
//        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(1L));
//        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
//        verify(employeeRepository).findById(Mockito.<Long>any());
//    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllEmployee() {
        ArrayList<Employee> content = new ArrayList<>();
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        EmployeeResponse actualAllEmployee = employeeServiceImpl.getAllEmployee(10, 3, "Sort By", "Dsc");
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllEmployee.getPageNumber());
        assertEquals(0, actualAllEmployee.getPageSize());
        assertEquals(0L, actualAllEmployee.getTotalElements());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertTrue(actualAllEmployee.isLastPage());
        assertEquals(content, actualAllEmployee.getContent());
    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllEmployee1() {
        ArrayList<Employee> content = new ArrayList<>();
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        EmployeeResponse actualAllEmployee = employeeServiceImpl.getAllEmployee(10, 3, "Sort By", "asc");
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllEmployee.getPageNumber());
        assertEquals(0, actualAllEmployee.getPageSize());
        assertEquals(0L, actualAllEmployee.getTotalElements());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertTrue(actualAllEmployee.isLastPage());
        assertEquals(content, actualAllEmployee.getContent());
    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllEmployee2() {
        Department department = new Department();
        department.setDeptName("asc");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        ArrayList<Employee> content = new ArrayList<>();
        content.add(employee);
        PageImpl<Employee> pageImpl = new PageImpl<>(content);
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        EmployeeResponse actualAllEmployee = employeeServiceImpl.getAllEmployee(10, 3, "Sort By", "Sort Dir");
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllEmployee.getPageNumber());
        assertEquals(1, actualAllEmployee.getPageSize());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertEquals(1, actualAllEmployee.getContent().size());
        assertEquals(1L, actualAllEmployee.getTotalElements());
        assertTrue(actualAllEmployee.isLastPage());
    }

    /**
     * Method under test:
     * {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllEmployee3() {
        Department department = new Department();
        department.setDeptName("asc");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(2L);

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setEmailId("jane.doe@example.org");
        employee2.setFirstName("John");
        employee2.setId(2L);
        employee2.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee2.setLastName("Smith");
        employee2.setLevel(0L);
        employee2.setPassword("asc");
        employee2.setRoles(new HashSet<>());

        ArrayList<Employee> content = new ArrayList<>();
        content.add(employee2);
        content.add(employee);
        PageImpl<Employee> pageImpl = new PageImpl<>(content);
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        EmployeeResponse actualAllEmployee = employeeServiceImpl.getAllEmployee(10, 3, "Sort By", "Sort Dir");
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllEmployee.getPageNumber());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertEquals(2, actualAllEmployee.getPageSize());
        assertEquals(2, actualAllEmployee.getContent().size());
        assertEquals(2L, actualAllEmployee.getTotalElements());
        assertTrue(actualAllEmployee.isLastPage());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        doNothing().when(employeeRepository).delete(Mockito.<Employee>any());
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        employeeServiceImpl.deleteEmployee(1L);
        verify(employeeRepository).delete(Mockito.<Employee>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L)).when(employeeRepository)
                .delete(Mockito.<Employee>any());
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(1L));
        verify(employeeRepository).delete(Mockito.<Employee>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee3() {
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeByDepartment(Long)}
     */
    @Test
    void testGetEmployeeByDepartment() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(employeeRepository.findByDepartment(Mockito.<Department>any())).thenReturn(new ArrayList<>());
        List<EmployeeDTO> actualEmployeeByDepartment = employeeServiceImpl.getEmployeeByDepartment(1L);
        verify(employeeRepository).findByDepartment(Mockito.<Department>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        assertTrue(actualEmployeeByDepartment.isEmpty());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeByDepartment(Long)}
     */
    @Test
    void testGetEmployeeByDepartment2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(employeeRepository.findByDepartment(Mockito.<Department>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeByDepartment(1L));
        verify(employeeRepository).findByDepartment(Mockito.<Department>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeByDepartment(Long)}
     */
    @Test
    void testGetEmployeeByDepartment3() {
        Optional<Department> emptyResult = Optional.empty();
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeByDepartment(1L));
        verify(departmentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeByDepartment(Long)}
     */
    @Test
    void testGetEmployeeByDepartment4() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findByDepartment(Mockito.<Department>any())).thenReturn(employeeList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        List<EmployeeDTO> actualEmployeeByDepartment = employeeServiceImpl.getEmployeeByDepartment(1L);
        verify(employeeRepository).findByDepartment(Mockito.<Department>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualEmployeeByDepartment.size());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeByDepartment(Long)}
     */
    @Test
    void testGetEmployeeByDepartment5() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        Department department3 = new Department();
        department3.setDeptName("42");
        department3.setEmployeeList(new ArrayList<>());
        department3.setId(2L);

        Employee employee2 = new Employee();
        employee2.setDepartment(department3);
        employee2.setEmailId("jane.doe@example.org");
        employee2.setFirstName("John");
        employee2.setId(2L);
        employee2.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee2.setLastName("Smith");
        employee2.setLevel(0L);
        employee2.setPassword("Password");
        employee2.setRoles(new HashSet<>());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee2);
        employeeList.add(employee);
        when(employeeRepository.findByDepartment(Mockito.<Department>any())).thenReturn(employeeList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        List<EmployeeDTO> actualEmployeeByDepartment = employeeServiceImpl.getEmployeeByDepartment(1L);
        verify(employeeRepository).findByDepartment(Mockito.<Department>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        assertEquals(2, actualEmployeeByDepartment.size());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getEmployeeByDepartment(Long)}
     */
    @Test
    void testGetEmployeeByDepartment6() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findByDepartment(Mockito.<Department>any())).thenReturn(employeeList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.getEmployeeByDepartment(1L));
        verify(employeeRepository).findByDepartment(Mockito.<Department>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:  {@link EmployeeServiceImpl#serachEmployee(String)}
     */
    @Test
    void testSerachEmployee() {
        when(employeeRepository.findByFirstNameContaining(Mockito.<String>any())).thenReturn(new ArrayList<>());
        List<EmployeeDTO> actualSerachEmployeeResult = employeeServiceImpl.serachEmployee("Name");
        verify(employeeRepository).findByFirstNameContaining(Mockito.<String>any());
        assertTrue(actualSerachEmployeeResult.isEmpty());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#serachEmployee(String)}
     */
    @Test
    void testSerachEmployee2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findByFirstNameContaining(Mockito.<String>any())).thenReturn(employeeList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        List<EmployeeDTO> actualSerachEmployeeResult = employeeServiceImpl.serachEmployee("Name");
        verify(employeeRepository).findByFirstNameContaining(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        assertEquals(1, actualSerachEmployeeResult.size());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#serachEmployee(String)}
     */
    @Test
    void testSerachEmployee3() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        Department department2 = new Department();
        department2.setDeptName("42");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(2L);

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setEmailId("jane.doe@example.org");
        employee2.setFirstName("John");
        employee2.setId(2L);
        employee2.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee2.setLastName("Smith");
        employee2.setLevel(0L);
        employee2.setPassword("Password");
        employee2.setRoles(new HashSet<>());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee2);
        employeeList.add(employee);
        when(employeeRepository.findByFirstNameContaining(Mockito.<String>any())).thenReturn(employeeList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        List<EmployeeDTO> actualSerachEmployeeResult = employeeServiceImpl.serachEmployee("Name");
        verify(employeeRepository).findByFirstNameContaining(Mockito.<String>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        assertEquals(2, actualSerachEmployeeResult.size());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#serachEmployee(String)}
     */
    @Test
    void testSerachEmployee4() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findByFirstNameContaining(Mockito.<String>any())).thenReturn(employeeList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.serachEmployee("Name"));
        verify(employeeRepository).findByFirstNameContaining(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#dtoToEmployee(EmployeeDTO)}
     */
    @Test
    void testDtoToEmployee() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Employee>>any())).thenReturn(employee);
        Employee actualDtoToEmployeeResult = employeeServiceImpl.dtoToEmployee(new EmployeeDTO());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
        assertSame(employee, actualDtoToEmployeeResult);
    }

    /**
     * Method under test:  {@link EmployeeServiceImpl#dtoToEmployee(EmployeeDTO)}
     */
    @Test
    void testDtoToEmployee2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Employee>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.dtoToEmployee(new EmployeeDTO()));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#EmployeeTodto(Employee)}
     */
    @Test
    void testEmployeeTodto() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(employeeDTO);

        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        EmployeeDTO actualEmployeeTodtoResult = employeeServiceImpl.EmployeeTodto(employee);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
        assertSame(employeeDTO, actualEmployeeTodtoResult);
    }

    /**
     * Method under test:  {@link EmployeeServiceImpl#EmployeeTodto(Employee)}
     */
    @Test
    void testEmployeeTodto2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.EmployeeTodto(employee));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
    }

    @Test
    void testUpdateEmployeeDetailsFailure() {
        // Arrange
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        // Mock the behavior of findById to throw an exception
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImpl.updateEmployeeDetails(employeeDTO, employeeId);
        });

        // Verify that save method is not called when findById fails
        verify(employeeRepository, never()).save(any());
    }

    @Test
    public void testRegisterNewUserWithInvalidDepartmentId() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Long invalidDepartmentId = 123L; // Assuming this departmentId is invalid

        // Mock the behavior of departmentRepository.findById to return null
        when(departmentRepository.findById(invalidDepartmentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImpl.registerNewUser(employeeDTO, invalidDepartmentId);
        });

        // Verify that departmentRepository.findById is called with the correct argument
        verify(departmentRepository, times(1)).findById(invalidDepartmentId);
    }

    @Test
    public void testAddEmployeeWithInvalidDepartmentId() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Long invalidDepartmentId = 123L; // Assuming this departmentId is invalid

        // Mock the behavior of departmentRepository.findById to return null
        when(departmentRepository.findById(invalidDepartmentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImpl.addEmployee(employeeDTO, invalidDepartmentId);
        });

        // Verify that departmentRepository.findById is called with the correct argument
        verify(departmentRepository, times(1)).findById(invalidDepartmentId);
    }

    @Test
    public void testGetEmployeeByIdWithInvalidId() {
        // Arrange
        Long invalidEmployeeId = 123L; // Assuming this employeeId is invalid

        // Mock the behavior of employeeRepository.findById to return null
        when(employeeRepository.findById(invalidEmployeeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImpl.getEmployeeById(invalidEmployeeId);
        });

        // Verify that employeeRepository.findById is called with the correct argument
        verify(employeeRepository, times(1)).findById(invalidEmployeeId);
    }
}
