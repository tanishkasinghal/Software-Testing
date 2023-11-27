package com.speMajor.demo.payload;


import com.speMajor.demo.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private long id;
    @NotEmpty
    private String firstName;
    private String lastName;
    @Email(message = "Email Address is not valid")
    private String emailId;
    @NotEmpty
    private String password;
    private DepartmentDTO department;
    private Date joiningDate;
    private Set<RoleDTO> roles=new HashSet<>();
}
