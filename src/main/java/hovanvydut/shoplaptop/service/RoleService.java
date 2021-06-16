package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.role.CreateRoleDto;
import hovanvydut.shoplaptop.dto.role.RoleDto;
import hovanvydut.shoplaptop.dto.role.UpdateRoleDto;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

/**
 * @author hovanvydut
 * @created 5/25/21
 */

public interface RoleService {

    public Page<RoleDto> getAllRole(int page, int size, String keyword, String[] sort);

    public RoleDto getRoleById(int id);

    public RoleDto createRole(@Valid CreateRoleDto createRoleDto);

    public RoleDto updateRole(@Valid UpdateRoleDto updateRoleDto, int id);

    public void deleteRole(int id);
}
