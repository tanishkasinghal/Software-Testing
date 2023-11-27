package com.speMajor.demo.service.BlackBox;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.speMajor.demo.model.Role;
import com.speMajor.demo.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplDiffblueTest {
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    /**
     * Method under test: {@link RoleServiceImpl#addRole(Role)}
     */
    @Test
    void testAddRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("Name");
        when(roleRepository.save(Mockito.<Role>any())).thenReturn(role);

        Role role2 = new Role();
        role2.setId(1);
        role2.setName("Name");
        Role actualAddRoleResult = roleServiceImpl.addRole(role2);
        verify(roleRepository).save(Mockito.<Role>any());
        assertSame(role, actualAddRoleResult);
    }
}
