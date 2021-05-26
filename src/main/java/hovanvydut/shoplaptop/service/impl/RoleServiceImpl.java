package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.dto.mapper.RoleMapper;
import hovanvydut.shoplaptop.dto.model.RoleDto;
import hovanvydut.shoplaptop.exception.RoleNotFoundException;
import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.repository.RoleRepository;
import hovanvydut.shoplaptop.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@Service
public class RoleServiceImpl implements RoleService {

    public RoleRepository roleRepo;

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
    public RoleDto createRole(RoleDto roleDto) {
        Role role = RoleMapper.MAPPER.toRole(roleDto);

        role = this.roleRepo.save(role);

        return RoleMapper.MAPPER.fromRole(role);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, int id) {
        System.out.println(roleDto);
        Optional<Role> roleOpt = this.roleRepo.findById(id);

        Function<Role, RoleDto> saveRoleFunc = (role) -> {
            role.setName(roleDto.getName());
            role.setDescription(roleDto.getDescription());

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
