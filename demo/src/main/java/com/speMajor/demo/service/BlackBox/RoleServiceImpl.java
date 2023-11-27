package com.speMajor.demo.service.BlackBox;

import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Role;
import com.speMajor.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role addRole(Role role) {
        Role newrole=this.roleRepository.save(role);
        return newrole;
    }
}
