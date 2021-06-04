package hovanvydut.shoplaptop.dto.brand;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDto {

    private int id;
    private String name;
    private String logo;
    private Set<CategoryDto> categories = new HashSet<>();

}
