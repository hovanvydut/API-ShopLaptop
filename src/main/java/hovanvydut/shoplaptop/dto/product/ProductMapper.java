package hovanvydut.shoplaptop.dto.product;

import hovanvydut.shoplaptop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/7/21
 */

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto fromProduct(Product product);

    List<ProductDto> fromProduct(List<Product> productList);
}
