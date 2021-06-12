package hovanvydut.shoplaptop.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author hovanvydut
 * Created on 6/12/21
 */

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class UpdateProductDto {
    @NotBlank
    private String name;

    private String slug;

    @NotBlank
    private String shortDescription;

    @NotBlank
    private String fullDescription;

    private boolean enabled;

    private boolean inStock;

    @Min(0)
    private float cost;

    @Min(0)
    private float price;

    @Min(0)
    @Max(100)
    private float discountPercent;

    @Min(0)
    private float length;

    @Min(0)
    private float width;

    @Min(0)
    private float height;

    @Min(0)
    private float weight;

    private MultipartFile mainImage;

    private int categoryId;

    private int brandId;

    private MultipartFile[] extraImages;

    private String[] detailNames;

    private String[] detailValues;

}
