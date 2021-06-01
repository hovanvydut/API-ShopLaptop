package hovanvydut.shoplaptop.dto.category;

import hovanvydut.shoplaptop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Mapper
public interface CategoryMapper {

    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    public CategoryDto fromCategory(Category category);

    public List<CategoryDto> fromCategoryList(List<Category> listCategory);

    public Set<CategoryDto> fromCategorySet(Set<Category> categorySet);
}
