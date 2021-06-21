package hovanvydut.shoplaptop.controller.v1.metadata.user;

import hovanvydut.shoplaptop.controller.v1.api.UserController;
import hovanvydut.shoplaptop.controller.v1.mapper.UserDtoMapper;
import hovanvydut.shoplaptop.dto.user.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserMetadata> {

    public UserAssembler() {
        super(UserController.class, UserMetadata.class);
    }

    @Override
    public UserMetadata toModel(UserDto entity) {
        UserMetadata userMetadata = UserDtoMapper.MAPPER.fromUserDto(entity);

        userMetadata.add(linkTo(methodOn(UserController.class).getOne(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUser(null, null, null, null)).withRel("users"));

        return userMetadata;
    }

    @Override
    public CollectionModel<UserMetadata> toCollectionModel(Iterable<? extends UserDto> entities) {
        Iterator<? extends UserDto> it = entities.iterator();
        List<UserMetadata> list = new ArrayList<>();

        while (it.hasNext()) {
            list.add(toModel(it.next()));
        }
        return CollectionModel.of(list,
                linkTo(methodOn(UserController.class).getAllUser(null, null, null, null)).withSelfRel());
    }
}
