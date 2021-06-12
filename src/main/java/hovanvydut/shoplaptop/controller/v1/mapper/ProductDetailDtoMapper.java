package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.product.ProductDetailMetadata;
import hovanvydut.shoplaptop.dto.product.ProductDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Mapper
public interface ProductDetailDtoMapper {

    ProductDetailDtoMapper MAPPER = Mappers.getMapper(ProductDetailDtoMapper.class);

    ProductDetailMetadata from (ProductDetailDto productDetailDto);

    List<ProductDetailMetadata> from (List<ProductDetailDto> productDetailDtos);

}
