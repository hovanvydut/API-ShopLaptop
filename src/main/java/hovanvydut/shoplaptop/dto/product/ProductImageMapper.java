package hovanvydut.shoplaptop.dto.product;

import hovanvydut.shoplaptop.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Mapper
public interface ProductImageMapper {

    ProductImageMapper MAPPER = Mappers.getMapper(ProductImageMapper.class);

    ProductImageDto from (ProductImage productImage);

    Set<ProductImageDto> from (Set<ProductImage> productImages);

}
