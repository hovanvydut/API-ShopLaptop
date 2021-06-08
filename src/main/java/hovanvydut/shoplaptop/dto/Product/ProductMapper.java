package hovanvydut.shoplaptop.dto.Product;

import hovanvydut.shoplaptop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 6/7/21
 */

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto fromProduct(Product product);

}
