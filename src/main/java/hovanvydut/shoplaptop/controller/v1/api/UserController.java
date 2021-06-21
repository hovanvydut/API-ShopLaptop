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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.USERS_PER_PAGE;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final PagedResourcesAssembler<UserDto> pagedResourcesAssembler;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService,
                          UserAssembler userAssembler,
                          PagedResourcesAssembler<UserDto> pagedResourcesAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public ResponseEntity<PagedModel<UserMetadata>> getAllUser(@RequestParam(required = false) Optional<String> keyword,
                                                                    @RequestParam(required = false) Optional<Integer> page,
                                                                    @RequestParam(required = false) Optional<Integer> size,
                                                                    @RequestParam(required = false, defaultValue = "id,asc") String[] sort) {

        Page<UserDto> paged = this.userService.getAllUser(page.orElse(1), size.orElse(USERS_PER_PAGE), keyword.orElse(""), sort);
        PagedModel<UserMetadata> metaCollection = this.pagedResourcesAssembler.toModel(paged, this.userAssembler);

        return ResponseEntity.ok(metaCollection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMetadata> getOne(@PathVariable("id") int id) {
        UserDto userDto = this.userService.getUserById(id);

        return ResponseEntity.ok(this.userAssembler.toModel(userDto));
    }

    @GetMapping("/export/pdf")
    public void exportUserToPdf(HttpServletResponse response) throws IOException {
        this.userService.exportUserToPdf(response);
    }

    @GetMapping("/export/excel")
    public void exportUserToExcel(HttpServletResponse response) throws IOException {
        this.userService.exportUserToExcel(response);
    }

    @GetMapping("/export/csv")
    public void exportUserToCsv(HttpServletResponse response) throws IOException {
        this.userService.exportUserToCsv(response);
    }

    @PostMapping()
    public ResponseEntity<UserMetadata> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        CreateUserDto createUserDto = UserDtoMapper.MAPPER.toCreateUserDto(createUserRequest);
        UserDto savedUserDto = this.userService.createUser(createUserDto);

        return ResponseEntity.ok(this.userAssembler.toModel(savedUserDto));
    }

    @PostMapping("/{id}/photos")
    public ResponseEntity<String> uploadAvatar(@RequestParam("image")MultipartFile multipartFile, @PathVariable("id") int id)
            throws IOException {

        return ResponseEntity.ok(this.userService.uploadPhotoForUser(id, multipartFile));
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
