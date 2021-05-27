package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.dto.user.CreateUserDto;
import hovanvydut.shoplaptop.dto.user.UpdateUserDto;
import hovanvydut.shoplaptop.dto.user.UserMapper;
import hovanvydut.shoplaptop.dto.user.UserDto;
import hovanvydut.shoplaptop.exception.UserNotFoundException;
import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.model.User;
import hovanvydut.shoplaptop.repository.UserRepository;
import hovanvydut.shoplaptop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Validated
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> list = new ArrayList<>();
        Iterator<User> it = this.userRepo.findAll().iterator();

        while (it.hasNext()) {
            list.add(UserMapper.MAPPER.userToUserDto(it.next()));
        }

        return list;
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> userOpt = this.userRepo.findById(id);

        return userOpt
                .map(user -> UserMapper.MAPPER.userToUserDto(user))
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public UserDto createUser(@Valid CreateUserDto createUserDto) {
        User existingUser = this.userRepo.findByEmail(createUserDto.getEmail());

        if (existingUser != null) {
            throw new RuntimeException("Email is existing");
        }

        User user = UserMapper.MAPPER.userDtotoUser(createUserDto);

        User savedUser = this.userRepo.save(user);

        return UserMapper.MAPPER.userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(@Valid UpdateUserDto updateUserDto, int id) {
        Optional<User> userOpt = this.userRepo.findById(id);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User with id = " + id + " not found");
        }

        User user = userOpt.get()
                .setFirstName(updateUserDto.getFirstName())
                .setLastName(updateUserDto.getLastName())
                .setPhotos(updateUserDto.getPhotos());

        if (updateUserDto.getPassword() == null) {
            user.setPassword(userOpt.get().getPassword());
        } else {
            user.setPassword(updateUserDto.getPassword());
        }

        if (updateUserDto.getRoles().size() > 0) {
            for (int roleId : updateUserDto.getRoles()) {
                user.addRole(new Role().setId(roleId));
            }
        }

        User savedUser = this.userRepo.save(user);

        return UserMapper.MAPPER.userToUserDto(savedUser);
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userOpt = this.userRepo.findById(id);

        if (userOpt.isEmpty())
            throw new UserNotFoundException();
        else
            this.userRepo.delete(userOpt.get());
    }
}
