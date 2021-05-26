package hovanvydut.shoplaptop.dto.mapper;

import hovanvydut.shoplaptop.dto.model.RoleDto;
import hovanvydut.shoplaptop.model.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@Mapper()
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDto roleDto);

    RoleDto fromRole(Role role);
}
