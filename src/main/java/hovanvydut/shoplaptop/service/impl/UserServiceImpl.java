package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.common.exporter.UserCsvExporter;
import hovanvydut.shoplaptop.common.exporter.UserExcelExporter;
import hovanvydut.shoplaptop.common.exporter.UserPdfExporter;
import hovanvydut.shoplaptop.dto.user.CreateUserDto;
import hovanvydut.shoplaptop.dto.user.UpdateUserDto;
import hovanvydut.shoplaptop.dto.user.UserDto;
import hovanvydut.shoplaptop.dto.user.UserMapper;
import hovanvydut.shoplaptop.exception.ImageSizeLimitExceededException;
import hovanvydut.shoplaptop.exception.UserNotFoundException;
import hovanvydut.shoplaptop.model.Category;
import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.model.User;
import hovanvydut.shoplaptop.repository.UserRepository;
import hovanvydut.shoplaptop.service.UserService;
import hovanvydut.shoplaptop.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.CATEGORIES_PER_PAGE;
import static hovanvydut.shoplaptop.common.constant.PaginationConstant.USERS_PER_PAGE;
import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.USER_UPLOAD_DIR;
import static hovanvydut.shoplaptop.util.PagingAndSortingUtil.processSort;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Validated
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder myBcryptPasswordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepo,
                           PasswordEncoder myBcryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.myBcryptPasswordEncoder = myBcryptPasswordEncoder;
    }

    @Override
    public Page<UserDto> getAllUser(int page, int size, String keyword, String[] sort) {

        // set default value for
        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = USERS_PER_PAGE;
        }

        Sort sortObj = processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        Page<User> pageUser = null;

        if (keyword != null && keyword.isEmpty()) {
            pageUser = this.userRepo.findAll(pageable);
        } else {
            pageUser = this.userRepo.search(keyword, pageable);
        }

        List<UserDto> list = new ArrayList<>();
        Iterator<User> it = this.userRepo.findAll().iterator();

        List<User> userList = pageUser.getContent();
        List<UserDto> userDtos = UserMapper.MAPPER.userToUserDto(userList);

        return new PageImpl<>(userDtos, pageable, pageUser.getTotalElements());
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
        user.setPassword(this.myBcryptPasswordEncoder.encode(user.getPassword()));

        User savedUser;

        savedUser = this.userRepo.save(user);

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
    public String uploadPhotoForUser(int id, MultipartFile multipartFile) throws IOException {
        Optional<User> userOpt = this.userRepo.findById(id);

        User user = userOpt.orElseThrow(() -> new UserNotFoundException());

        // size <= 500Kb
        int maxSizeUploadFile = 500 * 1024;

        if (!multipartFile.isEmpty()) {
            if (multipartFile.getSize() > (maxSizeUploadFile)) {
                throw new ImageSizeLimitExceededException("Size file exceed 500Kb");
            }

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            String uploadDir = USER_UPLOAD_DIR + id;

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            this.userRepo.save(user);

            return "/assets/img/user-photos/" + id + "/" + fileName;
        }

        return null;
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userOpt = this.userRepo.findById(id);

        if (userOpt.isEmpty())
            throw new UserNotFoundException();
        else
            this.userRepo.delete(userOpt.get());
    }

    @Override
    public void exportUserToPdf(HttpServletResponse response) throws IOException {
        List<User> listUsers = (List<User>)this.userRepo.findAll();
        UserPdfExporter exporter = new UserPdfExporter();

        exporter.export(listUsers, response);
    }

    @Override
    public void exportUserToExcel(HttpServletResponse response) throws IOException {
        List<User> listUsers = (List<User>)this.userRepo.findAll();
        UserExcelExporter exporter = new UserExcelExporter();

        exporter.export(listUsers, response);
    }

    @Override
    public void exportUserToCsv(HttpServletResponse response) throws IOException {
        List<User> listUsers = (List<User>)this.userRepo.findAll();
        UserCsvExporter exporter = new UserCsvExporter();

        exporter.export(listUsers, response);
    }
}
