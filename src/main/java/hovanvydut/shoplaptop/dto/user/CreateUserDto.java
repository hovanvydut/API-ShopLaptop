package hovanvydut.shoplaptop.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDto {

    @Email
    private String email;

    @Min(6)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String photos;

    private boolean enabled;

    List<Integer> roles;
}
