package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.user.CreateUserDto;
import hovanvydut.shoplaptop.dto.user.UpdateUserDto;
import hovanvydut.shoplaptop.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

public interface UserService {

    public Page<UserDto> getAllUser(int page, int size, String keyword, String[] sort);

    public  UserDto getUserById(int id);

    public UserDto createUser(@Valid CreateUserDto userDto);

    public UserDto updateUser(@Valid UpdateUserDto updateUserDto, int id);

    public String uploadPhotoForUser(int id, MultipartFile multipartFile) throws IOException;

    public void deleteUser(int id);

    void exportUserToPdf(HttpServletResponse response) throws IOException;

    void exportUserToExcel(HttpServletResponse response) throws IOException;

    void exportUserToCsv(HttpServletResponse response) throws IOException;
}
