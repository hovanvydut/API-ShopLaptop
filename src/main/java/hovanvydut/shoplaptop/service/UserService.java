package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(int userId);

    boolean deleteUserById(int userId);

    UserDto createNewUser(UserDto userDto) throws Exception;
}
