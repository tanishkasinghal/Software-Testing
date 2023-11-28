package com.speMajor.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="leave_application")
public class LeaveApplication {
    @Id
    private String applicationId;
    private String employeeId;
    private String leaveType;
    private String message;
    private Date leaveFrom;
    private Date leaveTill;
    private Date applicationDate;
    private Integer leaveStatus;
    private String remarks;
    private Date dateOfApproval;

}
