package hovanvydut.shoplaptop.dto.user;

import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Mapper
public abstract class UserMapper {

    public static UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    public abstract User userDtotoUser(UserDto userDto);


    public User userDtotoUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail())
                .setFirstName(createUserDto.getFirstName())
                .setLastName(createUserDto.getLastName())
                .setPassword(createUserDto.getPassword())
                .setEnabled(createUserDto.isEnabled());

        if (createUserDto.getPhotos() != null) {
            user.setPhotos(createUserDto.getPhotos());
        }

        if (createUserDto.getRoles().size() > 0) {
            for (int id : createUserDto.getRoles()) {
                user.addRole(new Role().setId(id));
            }
        }

        return user;
    }

    public abstract UserDto userToUserDto(User user);

    //public abstract UpdateUserDto toUpdateUserDto(UserDto userDto);
}
