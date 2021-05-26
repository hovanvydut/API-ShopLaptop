package hovanvydut.shoplaptop.controller.v1.metadata;

import hovanvydut.shoplaptop.controller.v1.request.CreateRoleRequest;
import hovanvydut.shoplaptop.controller.v1.request.UpdateRoleRequest;
import hovanvydut.shoplaptop.dto.model.RoleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Mapper
public interface RoleDtoMapper {

    RoleDtoMapper MAPPER = Mappers.getMapper(RoleDtoMapper.class);

    RoleDto toRoleDto(UpdateRoleRequest updateRoleRequest);


    RoleDto toRoleDto(CreateRoleRequest createRoleRequest);

    @InheritInverseConfiguration
    RoleMetadata fromRoleDto(RoleDto roleDto);
}
