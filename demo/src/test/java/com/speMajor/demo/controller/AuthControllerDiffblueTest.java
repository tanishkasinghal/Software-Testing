package com.speMajor.demo.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.payload.EmployeeDTO;
import com.speMajor.demo.payload.JwtAuthRequest;
import com.speMajor.demo.security.JwtTokenHelper;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private JwtTokenHelper jwtTokenHelper;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test:  {@link AuthController#createToken(JwtAuthRequest)}
     */
    @Test
    void testCreateToken() throws Exception {
        when(jwtTokenHelper.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new Employee());
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(jwtAuthRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\"}"));
    }


}
