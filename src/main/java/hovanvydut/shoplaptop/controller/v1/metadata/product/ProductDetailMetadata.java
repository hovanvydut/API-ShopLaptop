package hovanvydut.shoplaptop.controller.v1.metadata.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ProductDetailMetadata extends RepresentationModel<ProductDetailMetadata> {

    private int id;

    private String name;

    private String value;

}
