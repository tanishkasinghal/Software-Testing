package com.speMajor.demo.repository;


import com.speMajor.demo.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,String> {
    List<LeaveApplication> findAllByLeaveStatus(Integer status);
    List<LeaveApplication> findAllByEmployeeId(String id);
}
