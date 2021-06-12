package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.product.ProductMetadata;
import hovanvydut.shoplaptop.controller.v1.request.product.CreateProductRequest;
import hovanvydut.shoplaptop.controller.v1.request.product.UpdateProductRequest;
import hovanvydut.shoplaptop.dto.product.CreateProductDto;
import hovanvydut.shoplaptop.dto.product.ProductDto;
import hovanvydut.shoplaptop.dto.product.UpdateProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Mapper
public interface ProductDtoMapper {

    ProductDtoMapper MAPPER = Mappers.getMapper(ProductDtoMapper.class);

    ProductMetadata from (ProductDto productDto);

    List<ProductMetadata> from (List<ProductDto> productDtoList);

    CreateProductDto to (CreateProductRequest createProductRequest);

    UpdateProductDto to (UpdateProductRequest updateProductRequest);
}
