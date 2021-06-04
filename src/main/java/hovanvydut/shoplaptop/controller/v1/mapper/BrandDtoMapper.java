package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.brand.BrandMetadata;
import hovanvydut.shoplaptop.dto.brand.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Mapper
public interface BrandDtoMapper {

    BrandDtoMapper MAPPER = Mappers.getMapper(BrandDtoMapper.class);

    public BrandMetadata fromDto(BrandDto brandDto);

}
