package hovanvydut.shoplaptop.controller.v1.metadata.product;

import hovanvydut.shoplaptop.controller.v1.api.ProductController;
import hovanvydut.shoplaptop.controller.v1.mapper.ProductDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.brand.BrandAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.brand.BrandMetadata;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryMetadata;
import hovanvydut.shoplaptop.dto.product.ProductDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Component
public class ProductAssembler implements RepresentationModelAssembler<ProductDto, ProductMetadata> {

    @Override
    public ProductMetadata toModel(ProductDto entity) {

        ProductMetadata productMetadata = ProductDtoMapper.MAPPER.from(entity);

        productMetadata.add(linkTo(methodOn(ProductController.class).getOne(entity.getId())).withSelfRel());

        // category
        CategoryMetadata categoryMetadata = productMetadata.getCategory();
        CategoryAssembler.attachLink(categoryMetadata);

        // brand
        BrandMetadata brandMetadata = productMetadata.getBrand();
        BrandAssembler.attachLink(brandMetadata);

        // remove categories inside brand
        removeCategoriesInsideBrandField(productMetadata);

        // remove all children categories in a category
        removeAllChildrenCategoryinCategory(productMetadata);

        return productMetadata;
    }

    @Override
    public CollectionModel<ProductMetadata> toCollectionModel(Iterable<? extends ProductDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public static void removeCategoriesInsideBrandField(ProductMetadata productMetadata) {
        if (productMetadata != null && productMetadata.getBrand() != null) {
            productMetadata.getBrand().setCategories(null);
        }
    }

    public static void removeAllChildrenCategoryinCategory(ProductMetadata productMetadata) {
        if (productMetadata != null && productMetadata.getCategory() != null) {
            productMetadata.getCategory().setChildren(null);
        }
    }
}
