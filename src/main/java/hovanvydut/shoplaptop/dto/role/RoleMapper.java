package hovanvydut.shoplaptop.dto.role;

import hovanvydut.shoplaptop.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@Mapper()
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDto roleDto);

    Role toRole(CreateRoleDto createRoleDto);

    Role toRole(UpdateRoleDto updateRoleDto);

    RoleDto fromRole(Role role);

    List<RoleDto> fromRole(List<Role> roles);
}
