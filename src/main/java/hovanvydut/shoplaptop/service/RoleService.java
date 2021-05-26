package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.model.RoleDto;

import java.util.Set;

/**
 * @author hovanvydut
 * @created 5/25/21
 */

public interface RoleService {

    public Set<RoleDto> getAllRole();

    public RoleDto getRoleById(int id);

    public RoleDto createRole(RoleDto roleDto);

    public RoleDto updateRole(RoleDto roleDto, int id);

    public void deleteRole(int id);
}
