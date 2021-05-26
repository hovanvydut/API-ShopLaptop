package hovanvydut.shoplaptop.controller.v1.metadata;

import hovanvydut.shoplaptop.controller.v1.api.RoleController;
import hovanvydut.shoplaptop.dto.model.RoleDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Component
public class RoleAssembler extends RepresentationModelAssemblerSupport<RoleDto, RoleMetadata> {

    public RoleAssembler() {
        super(RoleController.class, RoleMetadata.class);
    }

    @Override
    public RoleMetadata toModel(RoleDto entity) {
        RoleMetadata roleMetadata = RoleDtoMapper.MAPPER.fromRoleDto(entity);
        roleMetadata.add(
                linkTo(methodOn(RoleController.class).getOne(entity.getId())).withSelfRel(),
                linkTo(methodOn(RoleController.class).getAllRole()).withRel("roles"));
        return roleMetadata;
    }

    @Override
    public CollectionModel<RoleMetadata> toCollectionModel(Iterable<? extends RoleDto> entities) {
        Iterator<? extends RoleDto> it = entities.iterator();
        List<RoleMetadata> list = new ArrayList<>();

        while (it.hasNext()) {
            RoleMetadata metadata = toModel(it.next());
            list.add(metadata);
        }

        return CollectionModel.of(list,
                linkTo(methodOn(RoleController.class).getAllRole()).withSelfRel());
    }
}
