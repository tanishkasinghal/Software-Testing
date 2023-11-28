package com.speMajor.demo.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.payload.ApiResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerDiffblueTest {
    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

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
        employeeDTO.setLevel(1L);
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
        employeeDTO.setLevel(1L);
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
                                "{\"id\":0,\"firstName\":null,\"lastName\":null,\"emailId\":null,\"level\":0,\"password\":null,\"department\":null,"
                                        + "\"joiningDate\":null,\"roles\":[]}"));
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        long employeeIdToDelete = 1L;

        // Act
        ResponseEntity<ApiResponse> responseEntity = employeeController.deleteEmployee(employeeIdToDelete);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new ApiResponse("User Deleted Successfully", true), responseEntity.getBody());

        // Verify that the service method was called
        verify(employeeService, times(1)).deleteEmployee(employeeIdToDelete);
    }

}
