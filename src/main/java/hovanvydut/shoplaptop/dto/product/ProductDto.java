package hovanvydut.shoplaptop.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.dto.brand.BrandDto;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/7/21
 */

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ProductDto {
    private Integer id;

    private String name;

    private String slug;

    private String shortDescription;

    private String fullDescription;

    private Date createdTime;

    private Date updatedTime;

    private boolean enabled;

    private boolean inStock;

    private float cost;

    private float price;

    private float discountPercent;

    private float length;

    private float width;

    private float height;

    private float weight;

    private String mainImage;

    private CategoryDto category;

    private BrandDto brand;

    private Set<ProductImageDto> images;

    private List<ProductDetailDto> details;
}
