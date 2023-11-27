package com.speMajor.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.service.Department.DepartmentService;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {DepartmentController.class})
@ExtendWith(SpringExtension.class)
class DepartmentControllerDiffblueTest {
    @Autowired
    private DepartmentController departmentController;

    @MockBean
    private DepartmentService departmentService;

    /**
     * Method under test:  {@link DepartmentController#addDepartment(DepartmentDTO)}
     */
    @Test
    void testAddDepartment() throws Exception {
        when(departmentService.addDepartment(Mockito.<DepartmentDTO>any())).thenReturn(new DepartmentDTO(1L, "Dept Name"));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptName("Dept Name");
        departmentDTO.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(departmentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/department/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(departmentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"deptName\":\"Dept Name\"}"));
    }

    /**
     * Method under test: {@link DepartmentController#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartment(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/department/{id}", 1L);
        MockMvcBuilders.standaloneSetup(departmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Dept Deleted Successfully\",\"success\":true}"));
    }

    /**
     * Method under test: {@link DepartmentController#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment2() throws Exception {
        doNothing().when(departmentService).deleteDepartment(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/department/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(departmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Dept Deleted Successfully\",\"success\":true}"));
    }

    /**
     * Method under test:  {@link DepartmentController#getAllDepartment()}
     */
    @Test
    void testGetAllDepartment() throws Exception {
        when(departmentService.getAllDepartments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/department/");
        MockMvcBuilders.standaloneSetup(departmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:  {@link DepartmentController#getDepartmentbyId(Long)}
     */
    @Test
    void testGetDepartmentbyId() throws Exception {
        when(departmentService.getDepartmentById(Mockito.<Long>any())).thenReturn(new DepartmentDTO(1L, "Dept Name"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/department/{id}", 1L);
        MockMvcBuilders.standaloneSetup(departmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"deptName\":\"Dept Name\"}"));
    }

    /**
     * Method under test:  {@link DepartmentController#updateDepartment(DepartmentDTO, Long)}
     */
    @Test
    void testUpdateDepartment() throws Exception {
        when(departmentService.updateDepartmentDetails(Mockito.<DepartmentDTO>any(), Mockito.<Long>any()))
                .thenReturn(new DepartmentDTO(1L, "Dept Name"));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptName("Dept Name");
        departmentDTO.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(departmentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/department/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(departmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"deptName\":\"Dept Name\"}"));
    }
}
