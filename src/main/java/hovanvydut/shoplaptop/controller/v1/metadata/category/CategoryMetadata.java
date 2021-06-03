package hovanvydut.shoplaptop.controller.v1.metadata.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CategoryMetadata extends RepresentationModel<CategoryMetadata> {

    private int id;
    private String name;
    private String slug;
    private String image;
    private String enabled;
    private Set<CategoryDto> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMetadata that = (CategoryMetadata) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
