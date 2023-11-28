package com.speMajor.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.service.Employee.EmployeeService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();
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
        employeeDTO.setLevel(1L);
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

//    @Test
//    void testUpdateEmployee() throws Exception {
//        EmployeeDTO existingEmployeeDTO = new EmployeeDTO();
//        // Set properties of existingEmployeeDTO
//
//        Mockito.when(employeeService.updateEmployeeDetails(Mockito.any(), Mockito.anyLong())).thenReturn(existingEmployeeDTO);
//
//        EmployeeDTO updatedEmployeeDTO = new EmployeeDTO();
//        // Set properties of updatedEmployeeDTO
//
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
//
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .put("/api/employee/{id}", 1L)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updatedEmployeeDTO));
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(existingEmployeeDTO.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updatedEmployeeDTO.getFirstName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(updatedEmployeeDTO.getLastName()));
//    }


    /**
     * Method under test: {@link EmployeeController#addEmployee(EmployeeDTO, Long)}
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
        employeeDTO.setLevel(1L);
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
                                "{\"id\":0,\"firstName\":null,\"lastName\":null,\"emailId\":null,\"level\":0,\"password\":null,\"department\":null,"
                                        + "\"joiningDate\":null,\"roles\":[]}"));
    }

    @Test
    void testGetEmployeesByDepartmentId() throws Exception {
        // Mock data
        Long departmentId = 1L;
        EmployeeDTO mockEmployeeDTO = new EmployeeDTO();
        mockEmployeeDTO.setId(1L);
        mockEmployeeDTO.setFirstName("John");
        mockEmployeeDTO.setLastName("Doe");

        // Mock service behavior
        when(employeeService.getEmployeeByDepartment(departmentId)).thenReturn(Collections.singletonList(mockEmployeeDTO));

        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        // Perform GET request
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/department/{deptId}/get", departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        // Verify the response content
        resultActions.andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }
}

