package hovanvydut.shoplaptop.dto.mapper;

import hovanvydut.shoplaptop.dto.model.RoleDto;
import hovanvydut.shoplaptop.dto.model.UserDto;
import hovanvydut.shoplaptop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhotos(user.getPhotos())
                .setEnabled(user.isEnabled())
                .setRoles(new HashSet<RoleDto>(
                    user.getRoles()
                        .stream()
                            .map(role -> new ModelMapper().map(role, RoleDto.class))
                            .collect(Collectors.toSet())
                ));
    }
}
