package hovanvydut.shoplaptop.controller.v1.metadata.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.controller.v1.metadata.role.RoleMetadata;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

/**
 * @author hovanvydut
 * Created on 5/27/21
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Relation(collectionRelation = "userList")
public class UserMetadata extends RepresentationModel<UserMetadata> {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String enabled;
    private String photos;
    Set<RoleMetadata> roles;

}
