package hovanvydut.shoplaptop.controller.v1.metadata.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hovanvydut.shoplaptop.controller.v1.metadata.brand.BrandMetadata;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryMetadata;
import hovanvydut.shoplaptop.dto.brand.BrandDto;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.dto.product.ProductDetailDto;
import hovanvydut.shoplaptop.dto.product.ProductImageDto;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
public class ProductMetadata extends RepresentationModel<ProductMetadata> {

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

    private CategoryMetadata category;

    private BrandMetadata brand;

    private Set<ProductImageMetadata> images;

    private List<ProductDetailMetadata> details;

}
