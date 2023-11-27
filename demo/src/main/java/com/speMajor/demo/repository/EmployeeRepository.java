package com.speMajor.demo.repository;

import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartment(Department department);
    List<Employee> findByFirstNameContaining(String firstName);

    Optional<Employee> findByEmailId(String emailId);

//    @Query("select emp from Employee emp where emp.firstName like :key")
//    List<Employee> searchByFirstName(@Param("key") String firstName);
}
