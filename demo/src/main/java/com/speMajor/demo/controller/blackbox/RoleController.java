package com.speMajor.demo.controller.blackbox;


import com.speMajor.demo.model.Role;
import com.speMajor.demo.service.BlackBox.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> addDepartment(@Valid @RequestBody Role role){
        Role newRole=this.roleService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    } //working
}
