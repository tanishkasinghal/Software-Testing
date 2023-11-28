package com.speMajor.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speMajor.demo.model.LeaveApplication;
import com.speMajor.demo.repository.LeaveApplicationRepository;
import com.speMajor.demo.service.Leave.LeaveApplicationService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

@ContextConfiguration(classes = {LeaveApplicationController.class})
@ExtendWith(SpringExtension.class)
class LeaveApplicationControllerDiffblueTest {
    @Autowired
    private LeaveApplicationController leaveApplicationController;

    @MockBean
    private LeaveApplicationService leaveApplicationService;

    @MockBean
    private LeaveApplicationRepository leaveApplicationRepository;


    /**
     * Method under test:
     * {@link LeaveApplicationController#submitResponse(LeaveApplication)}
     */
    @Test
    void testSubmitResponse() throws Exception {
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication
                .setApplicationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication.setApplicationId("42");
        leaveApplication
                .setDateOfApproval(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication.setEmployeeId("42");
        leaveApplication
                .setLeaveFrom(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication.setLeaveStatus(1);
        leaveApplication
                .setLeaveTill(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication.setLeaveType("Leave Type");
        leaveApplication.setMessage("Not all who wander are lost");
        leaveApplication.setRemarks("Remarks");
        when(leaveApplicationService.submitResponse(Mockito.<LeaveApplication>any())).thenReturn(leaveApplication);

        LeaveApplication leaveApplication2 = new LeaveApplication();
        leaveApplication2
                .setApplicationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication2.setApplicationId("42");
        leaveApplication2
                .setDateOfApproval(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication2.setEmployeeId("42");
        leaveApplication2
                .setLeaveFrom(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication2.setLeaveStatus(1);
        leaveApplication2
                .setLeaveTill(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication2.setLeaveType("Leave Type");
        leaveApplication2.setMessage("Not all who wander are lost");
        leaveApplication2.setRemarks("Remarks");
        String content = (new ObjectMapper()).writeValueAsString(leaveApplication2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/leave/submitResponse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(leaveApplicationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"applicationId\":\"42\",\"employeeId\":\"42\",\"leaveType\":\"Leave Type\",\"message\":\"Not all who wander are"
                                        + " lost\",\"leaveFrom\":0,\"leaveTill\":0,\"applicationDate\":0,\"leaveStatus\":1,\"remarks\":\"Remarks\",\"dateOfApproval"
                                        + "\":0}"));
    }

    @Test
    void testApplyLeave_Success() {
        // Create a sample LeaveApplication for testing
        LeaveApplication inputApplication = new LeaveApplication();
        inputApplication.setEmployeeId("employee123");
        // Set other properties as needed

        // Mock the addapplication method to return a new application
        LeaveApplication expectedApplication = new LeaveApplication();
        when(leaveApplicationService.addapplication(any(LeaveApplication.class))).thenReturn(expectedApplication);

        // Call the applyLeave method
        ResponseEntity<LeaveApplication> responseEntity = leaveApplicationController.applyLeave(inputApplication);

        // Verify that addapplication was called with the correct input
        verify(leaveApplicationService).addapplication(inputApplication);

        // Verify that the response entity contains the expected application
        assertEquals(expectedApplication, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllPendingApplication() {
        // Arrange
        List<LeaveApplication> pendingApplications = Arrays.asList(
                new LeaveApplication("1", "employee1", "Vacation", "Reason", null, null,
                        null, 0, null, null),
                new LeaveApplication("2", "employee2", "Sick Leave", "Health reasons", null, null,
                        null, 0, null, null)
                // Add more pending applications as needed
        );

        // Mocking the service to return the list of pending applications
        when(leaveApplicationService.getAllPendingApplication()).thenReturn(pendingApplications);

        // Act
        ResponseEntity<List<LeaveApplication>> responseEntity = leaveApplicationController.getAllPendingApplication();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pendingApplications, responseEntity.getBody());

        // Verify that the service method was called
        verify(leaveApplicationService, times(1)).getAllPendingApplication();
    }

    @Test
    void testGetAllApprovedApplication() {
        // Arrange
        List<LeaveApplication> approvedApplications = Arrays.asList(
                new LeaveApplication("1", "employee1", "Vacation", "Reason", null, null,
                        null, 1, null, null),
                new LeaveApplication("2", "employee2", "Sick Leave", "Health reasons", null, null,
                        null, 1, null, null)
                // Add more approved applications as needed
        );

        // Mocking the service to return the list of approved applications
        when(leaveApplicationService.getAllApprovedApplication()).thenReturn(approvedApplications);

        // Act
        ResponseEntity<List<LeaveApplication>> responseEntity = leaveApplicationController.getAllApprovedApplication();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(approvedApplications, responseEntity.getBody());

        // Verify that the service method was called
        verify(leaveApplicationService, times(1)).getAllApprovedApplication();
    }

    @Test
    void testGetAllRejectedApplication() {
        // Arrange
        List<LeaveApplication> rejectedApplications = Arrays.asList(
                new LeaveApplication("1", "employee1", "Vacation", "Reason", null, null,
                        null, 2, null, null),
                new LeaveApplication("2", "employee2", "Sick Leave", "Health reasons", null, null,
                        null, 2, null, null)
                // Add more rejected applications as needed
        );

        // Mocking the service to return the list of rejected applications
        when(leaveApplicationService.getAllRejectedApplication()).thenReturn(rejectedApplications);

        // Act
        ResponseEntity<List<LeaveApplication>> responseEntity = leaveApplicationController.getAllRejectedApplication();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rejectedApplications, responseEntity.getBody());

        // Verify that the service method was called
        verify(leaveApplicationService, times(1)).getAllRejectedApplication();
    }
    @Test
    void testGetAllApplication() {
        // Arrange
        List<LeaveApplication> allApplications = Arrays.asList(
                new LeaveApplication("1", "employee1", "Vacation", "Reason", null, null,
                        null, 1, null, null),
                new LeaveApplication("2", "employee2", "Sick Leave", "Health reasons", null, null,
                        null, 1, null, null)
                // Add more applications as needed
        );

        // Mocking the service to return the list of all applications
        when(leaveApplicationService.getAllApplication()).thenReturn(allApplications);

        // Act
        ResponseEntity<List<LeaveApplication>> responseEntity = leaveApplicationController.getAllApplication();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allApplications, responseEntity.getBody());

        // Verify that the service method was called
        verify(leaveApplicationService, times(1)).getAllApplication();
    }

    @Test
    void testGetAllApplicationbyEmployeeId() {
        // Arrange
        String employeeId = "employee1";
        List<LeaveApplication> applicationsByEmployeeId = Arrays.asList(
                new LeaveApplication("1", "employee1", "Vacation", "Reason", null, null,
                        null, 1, null, null),
                new LeaveApplication("3", "employee1", "Training", "Professional development", null, null,
                        null, 1, null, null)
                // Add more applications for the specified employeeId as needed
        );

        // Mocking the service to return the list of applications for the specified employeeId
        when(leaveApplicationService.getAllApplicationbyEmployeeId(employeeId)).thenReturn(applicationsByEmployeeId);

        // Act
        ResponseEntity<List<LeaveApplication>> responseEntity = leaveApplicationController.getAllApplicationbyEmployeeId(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(applicationsByEmployeeId, responseEntity.getBody());

        // Verify that the service method was called
        verify(leaveApplicationService, times(1)).getAllApplicationbyEmployeeId(employeeId);
    }
}
