package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.mapper.UserDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.user.UserAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.user.UserMetadata;
import hovanvydut.shoplaptop.controller.v1.request.user.CreateUserRequest;
import hovanvydut.shoplaptop.controller.v1.request.user.UpdateUserRequest;
import hovanvydut.shoplaptop.dto.user.CreateUserDto;
import hovanvydut.shoplaptop.dto.user.UpdateUserDto;
import hovanvydut.shoplaptop.dto.user.UserDto;
import hovanvydut.shoplaptop.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserAssembler userAssembler;

    public UserController(UserService userService, UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<UserMetadata>> getAllUser() {
        List<UserDto> list = this.userService.getAllUser();
        CollectionModel<UserMetadata> metaCollection = this.userAssembler.toCollectionModel(list);

        return ResponseEntity.ok(metaCollection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMetadata> getOne(@PathVariable("id") int id) {
        UserDto userDto = this.userService.getUserById(id);

        return ResponseEntity.ok(this.userAssembler.toModel(userDto));
    }

    @PostMapping()
    public ResponseEntity<UserMetadata> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        CreateUserDto createUserDto = UserDtoMapper.MAPPER.toCreateUserDto(createUserRequest);
        UserDto savedUserDto = this.userService.createUser(createUserDto);

        return ResponseEntity.ok(this.userAssembler.toModel(savedUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMetadata> updateUser(@RequestBody UpdateUserRequest updateUserRequest,
                                              @PathVariable("id") int id) {
        UpdateUserDto updateUserDto = UserDtoMapper.MAPPER.toUpdateUserDto(updateUserRequest);
        UserDto userDto = this.userService.updateUser(updateUserDto, id);

        return ResponseEntity.ok(this.userAssembler.toModel(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") int id) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
