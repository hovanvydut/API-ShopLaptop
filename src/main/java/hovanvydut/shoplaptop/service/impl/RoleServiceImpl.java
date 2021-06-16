package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.controller.v1.api.CategoryController;
import hovanvydut.shoplaptop.dto.role.CreateRoleDto;
import hovanvydut.shoplaptop.dto.role.RoleDto;
import hovanvydut.shoplaptop.dto.role.RoleMapper;
import hovanvydut.shoplaptop.dto.role.UpdateRoleDto;
import hovanvydut.shoplaptop.exception.RoleNotFoundException;
import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.repository.RoleRepository;
import hovanvydut.shoplaptop.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@Validated
@Service
public class RoleServiceImpl implements RoleService {

    public RoleRepository roleRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Override
    public Set<RoleDto> getAllRole() {
        Iterator<Role> it = this.roleRepo.findAll().iterator();
        Set<RoleDto> list = new HashSet<>();

        while (it.hasNext()) {
            Role role = it.next();
            list.add(RoleMapper.MAPPER.fromRole(role));
        }

        return list;
    }

    @Override
    public RoleDto getRoleById(int id) {
       Optional<Role> roleOpt = this.roleRepo.findById(id);

       return roleOpt.map(RoleMapper.MAPPER::fromRole).orElseThrow(() -> new RoleNotFoundException());
    }

    @Override
    public RoleDto createRole(@Valid CreateRoleDto createRoleDto) {
        System.out.println("CreateRoleDto: " + createRoleDto);
        Role role = RoleMapper.MAPPER.toRole(createRoleDto);

        Role newRole = this.roleRepo.save(role);

        return RoleMapper.MAPPER.fromRole(newRole);
    }

    @Override
    public RoleDto updateRole(@Valid UpdateRoleDto updateRoleDto, int id) {
        System.out.println(updateRoleDto);
        Optional<Role> roleOpt = this.roleRepo.findById(id);

        Function<Role, RoleDto> saveRoleFunc = (role) -> {
            role.setName(updateRoleDto.getName());
            role.setDescription(updateRoleDto.getDescription());

            this.roleRepo.save(role);

            return RoleMapper.MAPPER.fromRole(role);
        };

        return roleOpt.map(saveRoleFunc).orElseThrow(() -> new RoleNotFoundException());
    }

    @Override
    public void deleteRole(int id) {
        Optional<Role> roleOpt = this.roleRepo.findById(id);

        if (roleOpt.isEmpty()) {
            throw new RoleNotFoundException();
        } else {
            this.roleRepo.deleteById(id);
        }
    }
}
