package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.user.UserMetadata;
import hovanvydut.shoplaptop.controller.v1.request.user.CreateUserRequest;
import hovanvydut.shoplaptop.controller.v1.request.user.UpdateUserRequest;
import hovanvydut.shoplaptop.dto.user.CreateUserDto;
import hovanvydut.shoplaptop.dto.user.UpdateUserDto;
import hovanvydut.shoplaptop.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@Mapper
public interface UserDtoMapper {

    UserDtoMapper MAPPER = Mappers.getMapper(UserDtoMapper.class);

    CreateUserDto toCreateUserDto(CreateUserRequest createUserRequest);

    UpdateUserDto toUpdateUserDto(UpdateUserRequest updateUserRequest);

    UserMetadata fromUserDto(UserDto userDto);
}
