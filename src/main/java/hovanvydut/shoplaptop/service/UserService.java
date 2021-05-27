package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.user.CreateUserDto;
import hovanvydut.shoplaptop.dto.user.UpdateUserDto;
import hovanvydut.shoplaptop.dto.user.UserDto;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

public interface UserService {

    public List<UserDto> getAllUser();

    public  UserDto getUserById(int id);

    public UserDto createUser(@Valid CreateUserDto userDto);

    public UserDto updateUser(@Valid UpdateUserDto updateUserDto, int id);

    public void deleteUser(int id);

}
