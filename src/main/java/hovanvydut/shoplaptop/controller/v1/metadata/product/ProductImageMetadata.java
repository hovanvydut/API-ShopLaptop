package hovanvydut.shoplaptop.controller.v1.metadata.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.dto.product.ProductImageDto;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

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
public class ProductImageMetadata extends RepresentationModel<ProductImageMetadata> {

    private int id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductImageMetadata that = (ProductImageMetadata) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

}
