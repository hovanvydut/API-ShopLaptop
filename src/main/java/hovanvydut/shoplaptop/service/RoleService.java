package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.role.CreateRoleDto;
import hovanvydut.shoplaptop.dto.role.RoleDto;
import hovanvydut.shoplaptop.dto.role.UpdateRoleDto;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author hovanvydut
 * @created 5/25/21
 */

public interface RoleService {

    public Set<RoleDto> getAllRole();

    public RoleDto getRoleById(int id);

    public RoleDto createRole(@Valid CreateRoleDto createRoleDto);

    public RoleDto updateRole(@Valid UpdateRoleDto updateRoleDto, int id);

    public void deleteRole(int id);
}
