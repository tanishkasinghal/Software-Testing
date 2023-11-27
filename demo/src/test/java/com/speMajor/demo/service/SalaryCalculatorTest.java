package com.speMajor.demo.service;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SalaryCalculator.class})
@ExtendWith(SpringExtension.class)
class SalaryCalculatorTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryCalculator salaryCalculator;

    /**
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertEquals(52500.0d, salaryCalculator.calculateSalary(employee, "Department"));
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertEquals(52500.0d, salaryCalculator.calculateSalary(employee, "Department"));
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
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertEquals(63000.0d, salaryCalculator.calculateSalary(employee, "Department"));
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
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculateSalary(employee, "Department"));
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
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculateSalary(employee, "Department"));
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
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertEquals(55000.0d, salaryCalculator.calculateSalary(employee, "HR"));
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
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertEquals(57500.0d, salaryCalculator.calculateSalary(employee, "IT"));
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
     * Method under test: {@link SalaryCalculator#calculateSalary(Employee, String)}
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
        assertEquals(57500.0d, salaryCalculator.calculateSalary(employee, "DEV"));
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
     * Method under test: {@link SalaryCalculator#findBaseSalary(long)}
     */
    @Test
    void testFindBaseSalary() {
        assertEquals(50000.0d, salaryCalculator.findBaseSalary(1L));
        assertEquals(60000.0d, salaryCalculator.findBaseSalary(5L));
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.findBaseSalary(-1L));
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.findBaseSalary(Long.MAX_VALUE));
        assertEquals(70000.0d, salaryCalculator.findBaseSalary(10L));
        assertEquals(80000.0d, salaryCalculator.findBaseSalary(15L));
    }

    /**
     * Method under test: {@link SalaryCalculator#calculate(Long)}
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
        assertEquals(52500.0d, salaryCalculator.calculate(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculate(Long)}
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
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculate(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
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
    }

    /**
     * Method under test: {@link SalaryCalculator#calculate(Long)}
     */
    @Test
    void testCalculate3() {
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        new IllegalArgumentException("foo");
        assertThrows(ResourceNotFoundException.class, () -> salaryCalculator.calculate(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateTax(Long)}
     */
    @Test
    void testCalculateTax() {
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
        assertEquals(5000.0d, salaryCalculator.calculateTax(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateTax(Long)}
     */
    @Test
    void testCalculateTax2() {
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
        employee.setLevel(5L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertEquals(9000.0d, salaryCalculator.calculateTax(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateTax(Long)}
     */
    @Test
    void testCalculateTax3() {
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
        employee.setLevel(-1L);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculateTax(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateTax(Long)}
     */
    @Test
    void testCalculateTax4() {
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
        employee.setLevel(Long.MAX_VALUE);
        employee.setPassword("iloveyou");
        employee.setRoles(new HashSet<>());
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculateTax(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateTax(Long)}
     */
    @Test
    void testCalculateTax5() {
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> salaryCalculator.calculateTax(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SalaryCalculator#calculateTax(Long)}
     */
    @Test
    void testCalculateTax6() {
        when(employeeRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("user"));
        assertThrows(IllegalArgumentException.class, () -> salaryCalculator.calculateTax(1L));
        verify(employeeRepository).findById(Mockito.<Long>any());
    }
}

