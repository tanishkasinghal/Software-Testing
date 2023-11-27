package com.speMajor.demo.service.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
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
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

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
class EmployeeServiceImplTest {
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
     * Method under test: {@link EmployeeServiceImpl#addEmployee(EmployeeDTO, Long)}
     */
    @Test
    void testAddEmployee() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(employeeRepository.save(Mockito.<Employee>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

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
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.addEmployee(new EmployeeDTO(), 1L));
        verify(departmentRepository).findById(Mockito.<Long>any());
        verify(employeeRepository).save(Mockito.<Employee>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#addEmployee(EmployeeDTO, Long)}
     */
    @Test
    void testAddEmployee2() {
        Optional<Department> emptyResult = Optional.empty();
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

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
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Employee>>any())).thenReturn(employee);
        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.addEmployee(new EmployeeDTO(), 1L));
        verify(departmentRepository).findById(Mockito.<Long>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllEmployee() {
        ArrayList<Employee> content = new ArrayList<>();
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        EmployeeResponse actualAllEmployee = employeeServiceImpl.getAllEmployee(10, 3, "Sort By", "Sort Dir");
        assertEquals(content, actualAllEmployee.getContent());
        assertTrue(actualAllEmployee.isLastPage());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertEquals(0L, actualAllEmployee.getTotalElements());
        assertEquals(0, actualAllEmployee.getPageSize());
        assertEquals(0, actualAllEmployee.getPageNumber());
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
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
        assertEquals(1, actualAllEmployee.getContent().size());
        assertTrue(actualAllEmployee.isLastPage());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertEquals(1L, actualAllEmployee.getTotalElements());
        assertEquals(1, actualAllEmployee.getPageSize());
        assertEquals(0, actualAllEmployee.getPageNumber());
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
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
        assertEquals(2, actualAllEmployee.getContent().size());
        assertTrue(actualAllEmployee.isLastPage());
        assertEquals(1, actualAllEmployee.getTotalPages());
        assertEquals(2L, actualAllEmployee.getTotalElements());
        assertEquals(2, actualAllEmployee.getPageSize());
        assertEquals(0, actualAllEmployee.getPageNumber());
        verify(employeeRepository).findAll(Mockito.<Pageable>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllEmployee4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.getContent()" because "pageEmployees" is null
        //       at com.speMajor.demo.service.Employee.EmployeeServiceImpl.getAllEmployee(EmployeeServiceImpl.java:95)
        //   See https://diff.blue/R013 to resolve this issue.

        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(null);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        employeeServiceImpl.getAllEmployee(10, 3, "Sort By", "Sort Dir");
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllEmployee5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at com.speMajor.demo.service.Employee.EmployeeServiceImpl.getAllEmployee(EmployeeServiceImpl.java:93)
        //   See https://diff.blue/R013 to resolve this issue.

        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(null);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        employeeServiceImpl.getAllEmployee(-1, 3, "Sort By", "Sort Dir");
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllEmployee6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Property must not be null or empty
        //       at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.base/java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:992)
        //       at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.speMajor.demo.service.Employee.EmployeeServiceImpl.getAllEmployee(EmployeeServiceImpl.java:90)
        //   See https://diff.blue/R013 to resolve this issue.

        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(null);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        employeeServiceImpl.getAllEmployee(-1, 3, "", "Sort Dir");
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getAllEmployee(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllEmployee7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at com.speMajor.demo.service.Employee.EmployeeServiceImpl.getAllEmployee(EmployeeServiceImpl.java:93)
        //   See https://diff.blue/R013 to resolve this issue.

        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(null);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any())).thenReturn(new EmployeeDTO());
        employeeServiceImpl.getAllEmployee(-1, 3, "Sort By", "asc");
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
        verify(employeeRepository).findById(Mockito.<Long>any());
        verify(employeeRepository).delete(Mockito.<Employee>any());
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
        verify(employeeRepository).findById(Mockito.<Long>any());
        verify(employeeRepository).delete(Mockito.<Employee>any());
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
        assertSame(employee, employeeServiceImpl.dtoToEmployee(new EmployeeDTO()));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#dtoToEmployee(EmployeeDTO)}
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
        assertSame(employeeDTO, employeeServiceImpl.EmployeeTodto(employee));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<EmployeeDTO>>any());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#EmployeeTodto(Employee)}
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

//    /**
//     * Method under test: {@link EmployeeServiceImpl#addEmployee(EmployeeDTO, Long)}
//     */
//    @Test
//    void testAddEmployee2() {
//        Optional<Department> emptyResult = Optional.empty();
//        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
//
//        Department department = new Department();
//        department.setDeptName("Dept Name");
//        department.setEmployeeList(new ArrayList<>());
//        department.setId(1L);
//
//        Employee employee = new Employee();
//        employee.setDepartment(department);
//        employee.setEmailId("42");
//        employee.setFirstName("Jane");
//        employee.setId(1L);
//        employee.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
//        employee.setLastName("Doe");
//        employee.setLevel(1L);
//        employee.setPassword("iloveyou");
//        employee.setRoles(new HashSet<>());
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Employee>>any())).thenReturn(employee);
//        assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.addEmployee(new EmployeeDTO(), 1L));
//        verify(departmentRepository).findById(Mockito.<Long>any());
//        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
//    }
}

