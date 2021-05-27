package hovanvydut.shoplaptop.controller.v1.request.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author hovanvydut
 * Created on 5/26/21
 */

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRoleRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

}
