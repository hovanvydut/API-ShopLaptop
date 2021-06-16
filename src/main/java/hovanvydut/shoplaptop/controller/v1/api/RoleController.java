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
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    public RoleController(RoleService roleService,
                          RoleAssembler roleAssembler) {
        this.roleService = roleService;
        this.roleAssembler = roleAssembler;
    }


    @GetMapping()
    public ResponseEntity<CollectionModel<RoleMetadata>> getAllRole() {
        Set<RoleDto> rolesDto = this.roleService.getAllRole();

        return ResponseEntity.ok(this.roleAssembler.toCollectionModel(rolesDto));
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
