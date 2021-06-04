package hovanvydut.shoplaptop.controller.v1.metadata.brand;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryMetadata;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class BrandMetadata extends RepresentationModel<BrandMetadata> {

    private int id;
    private String name;
    private String logo;
    private List<CategoryMetadata> categories = new ArrayList<>();

}
