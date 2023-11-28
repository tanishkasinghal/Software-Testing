package com.speMajor.demo.service.Other;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.model.Role;
import com.speMajor.demo.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SalaryService.class})
@ExtendWith(SpringExtension.class)
class SalaryServiceDiffblueTest {
    @MockBean
    private EmployeeRepository employeeRepository;
    //
    ////    @Autowired
    ////    private SalaryService salaryService;
    //
    //    @InjectMocks
    //    private SalaryService salaryService;

    //    @Mock
    //    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryService salaryService;

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary() {
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
        assertEquals(52500.0d, salaryService.calculateSalary(employee, "Department"));
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(1L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        double actualCalculateSalaryResult = salaryService.calculateSalary(employee, "Department");
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
        assertEquals(52500.0d, actualCalculateSalaryResult);
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary3() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(5L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        double actualCalculateSalaryResult = salaryService.calculateSalary(employee, "Department");
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
        assertEquals(63000.0d, actualCalculateSalaryResult);
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary4() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(-1L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> salaryService.calculateSalary(employee, "Department"));
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary5() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(Long.MAX_VALUE);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> salaryService.calculateSalary(employee, "Department"));
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary6() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(1L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        double actualCalculateSalaryResult = salaryService.calculateSalary(employee, "HR");
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
        assertEquals(55000.0d, actualCalculateSalaryResult);
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary7() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(1L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        double actualCalculateSalaryResult = salaryService.calculateSalary(employee, "IT");
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
        assertEquals(57500.0d, actualCalculateSalaryResult);
    }

    /**
     * Method under test: {@link SalaryService#findBaseSalary(long)}
     */
    @Test
    void testFindBaseSalary() {
        assertEquals(50000.0d, salaryService.findBaseSalary(1L));
        assertEquals(60000.0d, salaryService.findBaseSalary(5L));
        assertThrows(IllegalArgumentException.class, () -> salaryService.findBaseSalary(-1L));
        assertThrows(IllegalArgumentException.class, () -> salaryService.findBaseSalary(Long.MAX_VALUE));
        assertEquals(70000.0d, salaryService.findBaseSalary(10L));
        assertEquals(80000.0d, salaryService.findBaseSalary(15L));
    }

    @Test
    void testFindBaseSalary2() {
        assertEquals(50000.0d, salaryService.findBaseSalary(1L));
    }

    /**
     * Method under test: {@link SalaryService#calculateSalary(Employee, String)}
     */
    @Test
    void testCalculateSalary8() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenReturn(1L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
        employee.setDepartment(department);
        employee.setEmailId("42");
        employee.setFirstName("Jane");
        employee.setId(1L);
        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employee.setLastName("Doe");
        employee.setLevel(1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        double actualCalculateSalaryResult = salaryService.calculateSalary(employee, "DEV");
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
        assertEquals(57500.0d, actualCalculateSalaryResult);
    }

    @Test
    void testCalculateSalary1() {
        Employee employee = new Employee();
        employee.setLevel(3L);

        double salary = salaryService.calculateSalary(employee, "IT");

        // Assuming the base salary for level 3 is 60000 and the bonus for IT department is 15%
        assertEquals(57500.0, salary, 0.01);
    }

    @Test
    void testCalculateSalaryWithInvalidDepartment() {
        Employee employee = new Employee();
        employee.setLevel(20L);

        assertThrows(IllegalArgumentException.class, () -> salaryService.calculateSalary(employee, "IT"));
    }


    /**
     * Method under test: {@link SalaryService#calculate(Long)}
     */
    @Test
    void testCalculate() {
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
        double actualCalculateResult = salaryService.calculate(1L);
        verify(employeeRepository).findById(Mockito.<Long>any());
        assertEquals(52500.0d, actualCalculateResult);
    }

    /**
     * Method under test: {@link SalaryService#calculate(Long)}
     */
    @Test
    void testCalculate2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);
        Employee employee = mock(Employee.class);
        when(employee.getLevel()).thenThrow(new IllegalArgumentException("foo"));
        when(employee.getDepartment()).thenReturn(department2);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setEmailId(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setJoiningDate(Mockito.<Date>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        doNothing().when(employee).setLevel(anyLong());
        doNothing().when(employee).setPassword(Mockito.<String>any());
        doNothing().when(employee).setRoles(Mockito.<Set<Role>>any());
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
        assertThrows(IllegalArgumentException.class, () -> salaryService.calculate(1L));
        verify(employee).getDepartment();
        verify(employee).getLevel();
        verify(employee).setDepartment(Mockito.<Department>any());
        verify(employee).setEmailId(Mockito.<String>any());
        verify(employee).setFirstName(Mockito.<String>any());
        verify(employee).setId(anyLong());
        verify(employee).setJoiningDate(Mockito.<Date>any());
        verify(employee).setLastName(Mockito.<String>any());
        verify(employee).setLevel(anyLong());
        verify(employee).setPassword(Mockito.<String>any());
        verify(employee).setRoles(Mockito.<Set<Role>>any());
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryService#calculate(Long)}
     */
    @Test
    void testCalculate3() {
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        new IllegalArgumentException("foo");
        assertThrows(ResourceNotFoundException.class, () -> salaryService.calculate(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testCalculateTax() {
        Employee employee = new Employee();
        employee.setLevel(6L);

        // Mocking the repository response
        when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));

        double tax = salaryService.calculateTax(1L);

        // Assuming the base salary for level 6 is 60000, and it falls in the 15% tax bracket
        assertEquals(6000.0, tax, 0.01);
    }

    @Test
    void testCalculateTaxWithLowSalary() {
        Employee employee = new Employee();
        employee.setLevel(2L);

        // Mocking the repository response
        when(this.employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));

        double tax = salaryService.calculateTax(1L);

        // Assuming the base salary for level 2 is 50000, and it falls in the 10% tax bracket
        assertEquals(5000.0, tax, 0.01);
    }

    @Test
    void testCalculateTaxWithHighSalary() {
        Employee employee = new Employee();
        employee.setLevel(12L);

        // Mocking the repository response
        when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));

        double tax = salaryService.calculateTax(1L);

        // Assuming the base salary for level 12 is 70000, and it falls in the 20% tax bracket
        assertEquals(7000.0, tax, 0.01);
    }

    @Test
    void testCalculateTaxWithInvalidEmployee() {
        // Mocking the repository response with an empty optional, simulating an employee not found scenario
        when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> salaryService.calculateTax(1L));
    }

}
