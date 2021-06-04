package hovanvydut.shoplaptop.dto.brand;

import hovanvydut.shoplaptop.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Mapper
public interface BrandMapper {

    BrandMapper MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto from(Brand brand);

    List<BrandDto> from (List<Brand> brandList);

}
