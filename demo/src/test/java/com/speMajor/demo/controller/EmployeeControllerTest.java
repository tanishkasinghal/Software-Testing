package com.speMajor.demo.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.service.Employee.EmployeeService;

import java.time.LocalDate;
import java.time.ZoneOffset;
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
class EmployeeControllerTest {
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
}

