package hovanvydut.shoplaptop.controller.v1.metadata;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author hovanvydut
 * Created on 5/25/21
 */

@Relation(collectionRelation = "roleList")
@Getter
@Setter
public class RoleMetadata extends RepresentationModel<RoleMetadata> {

    private int id;
    private String name;
    private String description;

}
