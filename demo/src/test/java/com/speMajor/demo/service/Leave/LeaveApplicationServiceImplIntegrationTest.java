package com.speMajor.demo.service.Leave;

import com.speMajor.demo.config.AppConstants;
import com.speMajor.demo.model.LeaveApplication;
import com.speMajor.demo.repository.LeaveApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaveApplicationServiceImplIntegrationTest {

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private LeaveApplicationServiceImpl leaveApplicationService;

    @Test
    void testAddApplicationIntegration() {
        // Arrange
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setLeaveStatus(AppConstants.Application_PENDING);

        // Act
        LeaveApplication result = leaveApplicationService.addapplication(leaveApplication);

        // Assert
        assertNotNull(result.getApplicationId());
        assertEquals(AppConstants.Application_PENDING, result.getLeaveStatus());
        assertNotNull(result.getApplicationDate());

        // Verify that the data was saved in the actual database
        Optional<LeaveApplication> retrieved = leaveApplicationRepository.findById(result.getApplicationId());
        assertTrue(retrieved.isPresent());
        assertEquals(AppConstants.Application_PENDING, retrieved.get().getLeaveStatus());
    }

//    @Test
//    void testSubmitResponseIntegration() {
//        // Arrange
//        LeaveApplication leaveApplication = new LeaveApplication();
//        leaveApplication.setApplicationId("some_existing_id"); // Provide an existing application ID
//        leaveApplication.setLeaveStatus(AppConstants.Application_APPROVED);
//
//        // Act
//        LeaveApplication result = leaveApplicationService.submitResponse(leaveApplication);
//
//        // Assert
//        assertNotNull(result.getDateOfApproval());
//        assertEquals(AppConstants.Application_APPROVED, result.getLeaveStatus());
//
//        // Verify that the data was updated in the actual database
//        Optional<LeaveApplication> retrieved = leaveApplicationRepository.findById("some_existing_id");
//        assertTrue(retrieved.isPresent());
//        assertEquals(AppConstants.Application_APPROVED, retrieved.get().getLeaveStatus());
//    }
}