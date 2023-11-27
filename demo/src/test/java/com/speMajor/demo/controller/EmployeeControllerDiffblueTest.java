package com.speMajor.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.payload.EmployeeResponse;
import com.speMajor.demo.service.Employee.EmployeeService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerDiffblueTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeController#addEmployee(EmployeeDTO, Long)}
     */
    @Test
    void testAddEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartment(new DepartmentDTO(1L, "Dept Name"));
        employeeDTO.setEmailId("42");
        employeeDTO.setFirstName("Jane");
        employeeDTO.setId(1L);
        employeeDTO.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employeeDTO.setLastName("Doe");
        employeeDTO.setPassword("iloveyou");
        employeeDTO.setRoles(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(employeeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/employee/department/{deptId}/add", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test:  {@link EmployeeController#addEmployee(EmployeeDTO, Long)}
     */
    @Test
    void testAddEmployee2() throws Exception {
        when(employeeService.addEmployee(Mockito.<EmployeeDTO>any(), Mockito.<Long>any())).thenReturn(new EmployeeDTO());

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartment(new DepartmentDTO(1L, "Dept Name"));
        employeeDTO.setEmailId("jane.doe@example.org");
        employeeDTO.setFirstName("Jane");
        employeeDTO.setId(1L);
        employeeDTO.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employeeDTO.setLastName("Doe");
        employeeDTO.setPassword("iloveyou");
        employeeDTO.setRoles(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(employeeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/employee/department/{deptId}/add", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"firstName\":null,\"lastName\":null,\"emailId\":null,\"password\":null,\"department\":null,\"joiningDate"
                                        + "\":null,\"roles\":[]}"));
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employee/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"User Deleted Successfully\",\"success\":true}"));
    }

    /**
     * Method under test: {@link EmployeeController#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() throws Exception {
        doNothing().when(employeeService).deleteEmployee(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/employee/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"User Deleted Successfully\",\"success\":true}"));
    }

    /**
     * Method under test:  {@link EmployeeController#getAllEmployees(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployee(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new EmployeeResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employee/");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":null,\"pageNumber\":0,\"pageSize\":0,\"totalElements\":0,\"totalPages\":0,\"lastPage\":false}"));
    }

    /**
     * Method under test:  {@link EmployeeController#getEmployeebyId(Long)}
     */
    @Test
    void testGetEmployeebyId() throws Exception {
        when(employeeService.getEmployeeById(Mockito.<Long>any())).thenReturn(new EmployeeDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employee/{id}", 1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"firstName\":null,\"lastName\":null,\"emailId\":null,\"password\":null,\"department\":null,\"joiningDate"
                                        + "\":null,\"roles\":[]}"));
    }

    /**
     * Method under test:  {@link EmployeeController#getEmployeebyId(Long)}
     */
    @Test
    void testGetEmployeebyId2() throws Exception {
        when(employeeService.getAllEmployee(Mockito.<Integer>any(), Mockito.<Integer>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(new EmployeeResponse());
        when(employeeService.getEmployeeById(Mockito.<Long>any())).thenReturn(new EmployeeDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employee/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":null,\"pageNumber\":0,\"pageSize\":0,\"totalElements\":0,\"totalPages\":0,\"lastPage\":false}"));
    }

    /**
     * Method under test:  {@link EmployeeController#getEmployeesByDepartmentId(Long)}
     */
    @Test
    void testGetEmployeesByDepartmentId() throws Exception {
        when(employeeService.getEmployeeByDepartment(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employee/department/{deptId}/get",
                1L);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link EmployeeController#searchEmployeeByName(String)}
     */
    @Test
    void testSearchEmployeeByName() throws Exception {
        when(employeeService.serachEmployee(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/employee/search/{keyword}",
                "Keyword");
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link EmployeeController#updateEmployee(EmployeeDTO, Long)}
     */
    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartment(new DepartmentDTO(1L, "Dept Name"));
        employeeDTO.setEmailId("42");
        employeeDTO.setFirstName("Jane");
        employeeDTO.setId(1L);
        employeeDTO.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employeeDTO.setLastName("Doe");
        employeeDTO.setPassword("iloveyou");
        employeeDTO.setRoles(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(employeeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employee/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test:  {@link EmployeeController#updateEmployee(EmployeeDTO, Long)}
     */
    @Test
    void testUpdateEmployee2() throws Exception {
        when(employeeService.updateEmployeeDetails(Mockito.<EmployeeDTO>any(), Mockito.<Long>any()))
                .thenReturn(new EmployeeDTO());

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartment(new DepartmentDTO(1L, "Dept Name"));
        employeeDTO.setEmailId("jane.doe@example.org");
        employeeDTO.setFirstName("Jane");
        employeeDTO.setId(1L);
        employeeDTO.setJoiningDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        employeeDTO.setLastName("Doe");
        employeeDTO.setPassword("iloveyou");
        employeeDTO.setRoles(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(employeeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/employee/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"firstName\":null,\"lastName\":null,\"emailId\":null,\"password\":null,\"department\":null,\"joiningDate"
                                        + "\":null,\"roles\":[]}"));
    }
}
