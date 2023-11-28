package com.speMajor.demo.service.Leave;



import com.speMajor.demo.model.LeaveApplication;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LeaveApplicationService {
//    Department addDepartment(Department department);
//    List<Department> getAllDepartments();
//    Department getDepartmentById(String id);

    LeaveApplication addapplication(LeaveApplication leaveApplication);
    List<LeaveApplication> getAllApplication();

    List<LeaveApplication> getAllPendingApplication();
    List<LeaveApplication> getAllApprovedApplication();
    List<LeaveApplication> getAllRejectedApplication();

    List<LeaveApplication> getAllApplicationbyEmployeeId(String employeeId);
    LeaveApplication submitResponse(LeaveApplication leaveApplication);
}
