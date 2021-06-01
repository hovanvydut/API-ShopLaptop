package hovanvydut.shoplaptop.controller.v1.metadata.category;

import hovanvydut.shoplaptop.controller.v1.api.CategoryController;
import hovanvydut.shoplaptop.controller.v1.mapper.CategoryDtoMapper;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Component
public class CategoryAssembler implements RepresentationModelAssembler<CategoryDto, CategoryMetadata> {

    @Override
    public CategoryMetadata toModel(CategoryDto entity) {

        CategoryMetadata categoryMetadata = CategoryDtoMapper.MAPPER.fromDto(entity);
        categoryMetadata.add(linkTo(methodOn(CategoryController.class).getOne(entity.getId())).withSelfRel());

        return categoryMetadata;
    }

    @Override
    public CollectionModel<CategoryMetadata> toCollectionModel(Iterable<? extends CategoryDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
