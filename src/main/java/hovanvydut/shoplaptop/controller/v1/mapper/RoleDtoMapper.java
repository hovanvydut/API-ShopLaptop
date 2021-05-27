package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.role.RoleMetadata;
import hovanvydut.shoplaptop.controller.v1.request.role.CreateRoleRequest;
import hovanvydut.shoplaptop.controller.v1.request.role.UpdateRoleRequest;
import hovanvydut.shoplaptop.dto.role.RoleDto;
import hovanvydut.shoplaptop.dto.role.CreateRoleDto;
import hovanvydut.shoplaptop.dto.role.UpdateRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Mapper
public interface RoleDtoMapper {

    RoleDtoMapper MAPPER = Mappers.getMapper(RoleDtoMapper.class);

    UpdateRoleDto toRoleDto(UpdateRoleRequest updateRoleRequest);

    CreateRoleDto toRoleDto(CreateRoleRequest createRoleRequest);

    RoleMetadata fromRoleDto(RoleDto roleDto);
}
