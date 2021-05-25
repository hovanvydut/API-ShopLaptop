package hovanvydut.shoplaptop.dto.mapper;

import hovanvydut.shoplaptop.dto.model.RoleDto;
import hovanvydut.shoplaptop.model.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author hovanvydut
 * @created 5/25/21
 */

@DisplayName("Role Mapper Testing")
public class RoleMapperTest {

    @Test
    @DisplayName("Convert Role Entity --> Role DTO")
    public void testEntityToDto() {
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");
        role.setDescription("manage everything");

        System.out.println(role);
        RoleDto roleDto = RoleMapper.MAPPER.fromRole(role);
        System.out.println(roleDto.toString());

        Assertions.assertThat(roleDto.getId()).isEqualTo(1);
        Assertions.assertThat(roleDto.getName()).isEqualTo("ADMIN");
        Assertions.assertThat(roleDto.getDescription()).isEqualTo("manage everything");
    }

    @Test
    @DisplayName("Convert Role DTO --> Role Entity")
    public void testDtoToEntity() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setName("ADMIN");
        roleDto.setDescription("manage everything");

        Role role = RoleMapper.MAPPER.toRole(roleDto);

        Assertions.assertThat(role.getId()).isEqualTo(1);
        Assertions.assertThat(role.getName()).isEqualTo("ADMIN");
        Assertions.assertThat(role.getDescription()).isEqualTo("manage everything");
    }

}
