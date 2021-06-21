package hovanvydut.shoplaptop.service.impl;

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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.USERS_PER_PAGE;
import static hovanvydut.shoplaptop.util.PagingAndSortingUtil.processSort;

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
    public Page<RoleDto> getAllRole(int page, int size, String keyword, String[] sort) {
        // set default value for
        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = USERS_PER_PAGE;
        }

        Sort sortObj = processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        Page<Role> pageRole = null;

        if (keyword != null && keyword.isEmpty()) {
            pageRole = this.roleRepo.findAll(pageable);
        } else {
            pageRole = this.roleRepo.search(keyword, pageable);
        }

        List<Role> userList = pageRole.getContent();
        List<RoleDto> roleDtos = RoleMapper.MAPPER.fromRole(userList);

        return new PageImpl<>(roleDtos, pageable, pageRole.getTotalElements());
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
