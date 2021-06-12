package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.product.ProductImageMetadata;
import hovanvydut.shoplaptop.dto.product.ProductImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Mapper
public interface ProductImageDtoMapper {

    ProductImageDtoMapper MAPPER = Mappers.getMapper(ProductImageDtoMapper.class);

    ProductImageMetadata from (ProductImageDto productImageDto);

    Set<ProductImageMetadata> from (List<ProductImageDto> productImageDtos);

}
