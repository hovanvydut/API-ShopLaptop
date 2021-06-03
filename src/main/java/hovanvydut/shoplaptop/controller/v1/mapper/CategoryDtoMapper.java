package hovanvydut.shoplaptop.controller.v1.mapper;

import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryMetadata;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Mapper
public interface CategoryDtoMapper {

    CategoryDtoMapper MAPPER = Mappers.getMapper(CategoryDtoMapper.class);

    CategoryMetadata fromDto(CategoryDto categoryDto);
}
