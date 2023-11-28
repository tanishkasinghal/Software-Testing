package com.speMajor.demo.service.Leave;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.*;

import com.speMajor.demo.config.AppConstants;
import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.LeaveApplication;
import com.speMajor.demo.repository.LeaveApplicationRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LeaveApplicationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LeaveApplicationServiceImplDiffblueTest {
    @MockBean
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private LeaveApplicationService leaveApplicationService;


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
    @BeforeEach
    void setup() {
        List<LeaveApplication> applicationsbyId = new ArrayList<>();
        applicationsbyId.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), 0, null, null));
        applicationsbyId.add(new LeaveApplication("app2", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), 0, null, null));
        Mockito.when(leaveApplicationRepository.findAllByEmployeeId("emp1")).thenReturn(applicationsbyId);
        List<LeaveApplication> allApplications = new ArrayList<>();
        allApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), 0, null, null));
        allApplications.add(new LeaveApplication("app2", "emp2", "Planned Leave", "Family Vacation", null, null, new Date(), 1, "Approving", null));
        Mockito.when(leaveApplicationRepository.findAll()).thenReturn(allApplications);
    }
    @Test
    public void testGetAllApplicationbyEmployeeId() {
        String applicationId1 = "app1";
        String applicationId2 = "app2";
        List<LeaveApplication> applications=leaveApplicationService.getAllApplicationbyEmployeeId("emp1");
        Assertions.assertEquals(2, applications.size());
        Assertions.assertEquals(applicationId1, applications.get(0).getApplicationId());
        Assertions.assertEquals(applicationId2, applications.get(1).getApplicationId());
    }
    @Test
    public void testGetAllApplication() {
        String employeeId1 = "app1";
        String employeeId2 = "app2";
        List<LeaveApplication> applications = leaveApplicationService.getAllApplication();
        Assertions.assertEquals(2, applications.size());
        Assertions.assertEquals(employeeId1, applications.get(0).getApplicationId());
        Assertions.assertEquals(employeeId2, applications.get(1).getApplicationId());
    }

    @Test
    public void testAddApplication() {
        String expectedApplicationId = "0b87785a-118d-4511-882b-4587dacea1ad";
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setLeaveStatus(AppConstants.Application_PENDING);
        Mockito.when(leaveApplicationRepository.save(Mockito.any(LeaveApplication.class)))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        LeaveApplication result = leaveApplicationService.addapplication(leaveApplication);
        result.setApplicationId(expectedApplicationId);

        Assertions.assertEquals(expectedApplicationId, result.getApplicationId());
        Assertions.assertEquals(AppConstants.Application_PENDING, result.getLeaveStatus());
        assertNotNull(result.getApplicationDate());
    }
    @Test
    public void testSubmitResponse1() {
        String applicationId = "0b87785a-118d-4511-882b-4587dacea1ad";
        LeaveApplication existingApplication = new LeaveApplication();
        existingApplication.setApplicationId(applicationId);
        existingApplication.setLeaveType("Sick Leave");
        existingApplication.setMessage("Taking Sick Leave");
        existingApplication.setLeaveStatus(0);
        existingApplication.setLeaveFrom(new Date());
        existingApplication.setLeaveTill(new Date());
        existingApplication.setApplicationDate(new Date());
        existingApplication.setEmployeeId("emp1");

        LeaveApplication updatedApplication = new LeaveApplication();
        updatedApplication.setApplicationId(applicationId);
        updatedApplication.setLeaveType("Sick Leave");
        updatedApplication.setMessage("Sick Leave");
        updatedApplication.setRemarks("Approving");
        updatedApplication.setLeaveStatus(1);
        updatedApplication.setLeaveFrom(new Date());
        updatedApplication.setLeaveTill(new Date());
        updatedApplication.setApplicationDate(new Date());
        updatedApplication.setDateOfApproval(new Date());
        updatedApplication.setEmployeeId("emp1");

        // Mock the repository's findById method to return the existingApplication
        Mockito.when(leaveApplicationRepository.findById(applicationId)).thenReturn(Optional.of(existingApplication));

        // Mock the repository's save method to return the updatedApplication
        Mockito.when(leaveApplicationRepository.save(Mockito.any(LeaveApplication.class))).thenReturn(updatedApplication);

        // Invoke the submitResponse method
        LeaveApplication result = leaveApplicationService.submitResponse(updatedApplication);

        // Assertions
        Assertions.assertEquals(updatedApplication.getLeaveType(), result.getLeaveType());
        Assertions.assertEquals(updatedApplication.getMessage(), result.getMessage());
        assertNotNull(result.getDateOfApproval());
        Assertions.assertTrue(result.getLeaveStatus() == 1 || result.getLeaveStatus() == 2);
        Assertions.assertEquals(updatedApplication.getLeaveFrom(), result.getLeaveFrom());
        Assertions.assertEquals(updatedApplication.getLeaveTill(), result.getLeaveTill());
        Assertions.assertEquals(updatedApplication.getApplicationDate(), result.getApplicationDate());
        Assertions.assertEquals(updatedApplication.getEmployeeId(), result.getEmployeeId());
    }

    @Test
    public void testGetAllPendingApplication() {
        List<LeaveApplication> pendingApplications = new ArrayList<>();
        pendingApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), AppConstants.Application_PENDING, null, null));
        pendingApplications.add(new LeaveApplication("app2", "emp2", "Vacation Leave", "Taking Vacation Leave", null, null, new Date(), AppConstants.Application_PENDING, null, null));
        Mockito.when(leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_PENDING)).thenReturn(pendingApplications);

        List<LeaveApplication> result = leaveApplicationService.getAllPendingApplication();

        Assertions.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            LeaveApplication application = result.get(i);
            Assertions.assertEquals(AppConstants.Application_PENDING, application.getLeaveStatus());
            Assertions.assertEquals("app" + (i + 1), application.getApplicationId());
        }
    }

    @Test
    public void testGetAllApprovedApplication() {
        List<LeaveApplication> approvedApplications = new ArrayList<>();
        approvedApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), AppConstants.Application_APPROVED, null, null));
        approvedApplications.add(new LeaveApplication("app2", "emp2", "Vacation Leave", "Taking Vacation Leave", null, null, new Date(), AppConstants.Application_APPROVED, null, null));
        Mockito.when(leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_APPROVED)).thenReturn(approvedApplications);

        List<LeaveApplication> result = leaveApplicationService.getAllApprovedApplication();

        Assertions.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            LeaveApplication application = result.get(i);
            Assertions.assertEquals(AppConstants.Application_APPROVED, application.getLeaveStatus());
            Assertions.assertEquals("app" + (i + 1), application.getApplicationId());
        }
    }

    @Test
    public void testGetAllRejectedApplication() {
        List<LeaveApplication> rejectedApplications = new ArrayList<>();
        rejectedApplications.add(new LeaveApplication("app1", "emp1", "Sick Leave", "Taking Sick Leave", null, null, new Date(), AppConstants.Application_REJECTED, null, null));
        rejectedApplications.add(new LeaveApplication("app2", "emp2", "Vacation Leave", "Taking Vacation Leave", null, null, new Date(), AppConstants.Application_REJECTED, null, null));
        Mockito.when(leaveApplicationRepository.findAllByLeaveStatus(AppConstants.Application_REJECTED)).thenReturn(rejectedApplications);

        List<LeaveApplication> result = leaveApplicationService.getAllRejectedApplication();

        Assertions.assertEquals(2, result.size());
        for (int i = 0; i < result.size(); i++) {
            LeaveApplication application = result.get(i);
            Assertions.assertEquals(AppConstants.Application_REJECTED, application.getLeaveStatus());
            Assertions.assertEquals("app" + (i + 1), application.getApplicationId());
        }

    }


    /**
     * Method under test:
     * {@link LeaveApplicationServiceImpl#submitResponse(LeaveApplication)}
     */
    @Test
    void testSubmitResponse() {
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
        Optional<LeaveApplication> ofResult = Optional.of(leaveApplication);

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
        when(leaveApplicationRepository.save(Mockito.<LeaveApplication>any())).thenReturn(leaveApplication2);
        when(leaveApplicationRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        LeaveApplication leaveApplication3 = new LeaveApplication();
        leaveApplication3
                .setApplicationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication3.setApplicationId("42");
        leaveApplication3
                .setDateOfApproval(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication3.setEmployeeId("42");
        leaveApplication3
                .setLeaveFrom(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication3.setLeaveStatus(1);
        leaveApplication3
                .setLeaveTill(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        leaveApplication3.setLeaveType("Leave Type");
        leaveApplication3.setMessage("Not all who wander are lost");
        leaveApplication3.setRemarks("Remarks");
        LeaveApplication actualSubmitResponseResult = leaveApplicationService.submitResponse(leaveApplication3);
        verify(leaveApplicationRepository).findById(Mockito.<String>any());
        verify(leaveApplicationRepository).save(Mockito.<LeaveApplication>any());
        assertSame(leaveApplication2, actualSubmitResponseResult);
    }

    /**
     * Method under test:
     * {@link LeaveApplicationServiceImpl#submitResponse(LeaveApplication)}
     */
    @Test
    void testSubmitResponse2() {
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
        Optional<LeaveApplication> ofResult = Optional.of(leaveApplication);
        when(leaveApplicationRepository.save(Mockito.<LeaveApplication>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(leaveApplicationRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        assertThrows(ResourceNotFoundException.class, () -> leaveApplicationService.submitResponse(leaveApplication2));
        verify(leaveApplicationRepository).findById(Mockito.<String>any());
        verify(leaveApplicationRepository).save(Mockito.<LeaveApplication>any());
    }

    /**
     * Method under test:
     * {@link LeaveApplicationServiceImpl#submitResponse(LeaveApplication)}
     */
    @Test
    void testSubmitResponse3() {
        Optional<LeaveApplication> emptyResult = Optional.empty();
        when(leaveApplicationRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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
        assertThrows(ResourceNotFoundException.class, () -> leaveApplicationService.submitResponse(leaveApplication));
        verify(leaveApplicationRepository).findById(Mockito.<String>any());
    }


}
