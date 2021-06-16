package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.mapper.RoleDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.role.RoleAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.role.RoleMetadata;
import hovanvydut.shoplaptop.controller.v1.request.role.CreateRoleRequest;
import hovanvydut.shoplaptop.controller.v1.request.role.UpdateRoleRequest;
import hovanvydut.shoplaptop.dto.role.CreateRoleDto;
import hovanvydut.shoplaptop.dto.role.RoleDto;
import hovanvydut.shoplaptop.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.USERS_PER_PAGE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleAssembler roleAssembler;
    private final PagedResourcesAssembler<RoleDto> pagedResourcesAssembler;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    public RoleController(RoleService roleService,
                          RoleAssembler roleAssembler,
                          PagedResourcesAssembler<RoleDto> pagedResourcesAssembler) {
        this.roleService = roleService;
        this.roleAssembler = roleAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @GetMapping()
    public ResponseEntity<PagedModel<RoleMetadata>> getAllRole(@RequestParam(required = false) Optional<String> keyword,
                                                               @RequestParam(required = false) Optional<Integer> page,
                                                               @RequestParam(required = false) Optional<Integer> size,
                                                               @RequestParam(required = false, defaultValue = "id,asc") String[] sort) {
        Page<RoleDto> paged = this.roleService.getAllRole(page.orElse(1), size.orElse(USERS_PER_PAGE), keyword.orElse(""), sort);
        PagedModel<RoleMetadata> pagedModel = this.pagedResourcesAssembler.toModel(paged, this.roleAssembler);

        return ResponseEntity.ok(pagedModel);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleMetadata> getOne(@PathVariable Integer id) {
        RoleDto roleDto = this.roleService.getRoleById(id);

        return ResponseEntity.ok(this.roleAssembler.toModel(roleDto));
    }

    @PostMapping()
    public ResponseEntity<String> createRole(@Valid @RequestBody CreateRoleRequest createRoleRequest) {
        CreateRoleDto createRoleDto = RoleDtoMapper.MAPPER.toRoleDto(createRoleRequest);

        RoleDto newRoleDto = this.roleService.createRole(createRoleDto);
        URI newUri = linkTo(methodOn(RoleController.class).getOne(newRoleDto.getId())).toUri();

        return ResponseEntity.created(newUri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleMetadata> updateRole(@PathVariable Integer id,
                                             @Valid @RequestBody UpdateRoleRequest updateRoleRequest) {
        RoleDto newRoleDto = this.roleService.updateRole(RoleDtoMapper.MAPPER.toRoleDto(updateRoleRequest), id);

        return ResponseEntity.ok(RoleDtoMapper.MAPPER.fromRoleDto(newRoleDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) {
        this.roleService.deleteRole(id);

        return ResponseEntity.noContent().build();
    }
}
