package com.speMajor.demo.security;

import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //loading user from database by username
        Employee employee=this.employeeRepository.findByEmailId(username).orElseThrow(()-> new ResourceNotFoundException("user","email: "+username, 0L));
        return employee;
    }


}
