package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.controller.v1.request.CreateUserReq;
import hovanvydut.shoplaptop.dto.mapper.UserMapper;
import hovanvydut.shoplaptop.dto.model.UserDto;
import hovanvydut.shoplaptop.model.User;
import hovanvydut.shoplaptop.repository.UserRepository;
import hovanvydut.shoplaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<UserDto> getAllUsers() {
        return StreamSupport.stream(this.userRepo.findAll().spliterator(), false)
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(int userId) {
        Optional<User> userOpt = this.userRepo.findById(userId);

        return userOpt.map(UserMapper::toUserDto).or(Optional::empty);
    }

    @Override
    public boolean deleteUserById(int userId) {
        this.userRepo.deleteById(userId);
        return !this.userRepo.existsById(userId);
    }

    @Override
    public UserDto createNewUser(UserDto userDto) throws Exception {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        User existingUser = this.userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new Exception("User is existing");
        }

        User savedUser = this.userRepo.save(user);

        return UserMapper.toUserDto(savedUser);
    }
}
