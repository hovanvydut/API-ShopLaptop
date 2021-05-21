package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.request.CreateUserReq;
import hovanvydut.shoplaptop.dto.model.UserDto;
import hovanvydut.shoplaptop.controller.v1.response.Response;
import hovanvydut.shoplaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Response<UserDto> getUserById(@PathVariable("id") int userId) {
        System.out.println("xxx" + userId);
        Optional<UserDto> userDtoOpt = this.userService.getUserById(userId);

        if (userDtoOpt.isPresent()) {
            System.out.println(userDtoOpt.get());
            Response<UserDto> response = Response.OK();
            response.setPayload(userDtoOpt.get());
            return response;
        } else {
            return Response.OK();
        }
    }

    @PostMapping()
    public Response<UserDto> createNewUser(@Valid @RequestBody CreateUserReq createUserReq) {
        UserDto createUserDto = new UserDto().setEmail(createUserReq.getEmail())
                                            .setPassword(createUserReq.getPassword())
                                            .setFirstName(createUserReq.getFirstName())
                                            .setLastName(createUserReq.getLastName());
        UserDto savedUserDto = null;
        try {
            savedUserDto = this.userService.createNewUser(createUserDto);
        } catch (Exception e) {
            return Response.NOT_FOUND();
        }

        Response<UserDto> response = Response.OK();
        response.setPayload(savedUserDto);
        return response;
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteUserById(@PathVariable("id") int userId) {
        boolean result = this.userService.deleteUserById(userId);

        if (result) {
            return Response.OK();
        } else {
            return Response.NOT_FOUND();
        }
    }
}
