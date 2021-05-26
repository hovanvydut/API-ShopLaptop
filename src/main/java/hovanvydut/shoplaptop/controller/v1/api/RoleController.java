package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.metadata.RoleAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.RoleDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.RoleMetadata;
import hovanvydut.shoplaptop.controller.v1.request.CreateRoleRequest;
import hovanvydut.shoplaptop.controller.v1.request.UpdateRoleRequest;
import hovanvydut.shoplaptop.controller.v1.response.Response;
import hovanvydut.shoplaptop.dto.model.RoleDto;
import hovanvydut.shoplaptop.service.RoleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;


    private final RoleAssembler roleAssembler;

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
        RoleDto newRoleDto = this.roleService.createRole(RoleDtoMapper.MAPPER.toRoleDto(createRoleRequest));

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
