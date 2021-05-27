package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.role.CreateRoleDto;
import hovanvydut.shoplaptop.dto.role.RoleDto;
import hovanvydut.shoplaptop.dto.role.RoleMapper;
import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.repository.RoleRepository;
import hovanvydut.shoplaptop.service.impl.RoleServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    // the same (@Spy + @Mock), actual instance of roleRepository
    // roleRepository will be created in spring context
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void testGetRoleById() {
        int id = 1;
        Role seedRole = new Role("ADMIN", "manage everything");
        seedRole.setId(id);

        Mockito.when(this.roleRepository.findById(id)).thenReturn(Optional.of(seedRole));

        RoleDto roleDto = this.roleService.getRoleById(id);

        Assertions.assertThat(roleDto.getName()).isEqualTo("ADMIN");
        Assertions.assertThat(roleDto.getDescription()).isEqualTo("manage everything");
        Assertions.assertThat(roleDto.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test invalid input into createRole() method")
    public void testInvalidInputCreateRole() {
        CreateRoleDto createRoleDto = new CreateRoleDto().setName("ADMIN").setDescription("manage everything");
        Role role = RoleMapper.MAPPER.toRole(createRoleDto);
        Role newRole = RoleMapper.MAPPER.toRole(createRoleDto);
        newRole.setId(1);

        Mockito.when(this.roleRepository.save(role)).thenReturn(newRole);

        RoleDto testRole = this.roleService.createRole(createRoleDto);

        Assertions.assertThat(testRole.getId()).isEqualTo(1);
    }

}
