package hovanvydut.shoplaptop.controller.v1.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UpdateUserRequest {
    @Email(message = "Email is invalid")
    private String email;

    @Size(min = 6, max = 20)
    private String password;

    @Size(min = 1, max = 45)
    private String firstName;

    @Size(min = 1, max = 45)
    private String lastName;

    private boolean enabled;

    private Set<Integer> roles = new HashSet<>();
}
