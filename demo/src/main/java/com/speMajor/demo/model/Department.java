package com.speMajor.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dept_name",nullable = false)
    private String deptName;

    //ek dept me multiple emp ho skte hai to list
    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<Employee> employeeList=new ArrayList<>();

}
