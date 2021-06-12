package hovanvydut.shoplaptop.dto.product;

import hovanvydut.shoplaptop.model.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Mapper
public interface ProductDetailMapper {

    ProductDetailMapper MAPPER = Mappers.getMapper(ProductDetailMapper.class);

    ProductDetailDto from (ProductDetail productDetail);

    List<ProductDetailDto> from (List<ProductDetail> productDetails);

    ProductDetail toProductDetail (ProductDetailDto productDetailDto);

    List<ProductDetail> toProductDetail (List<ProductDetailDto> productDetailDtos);

}
