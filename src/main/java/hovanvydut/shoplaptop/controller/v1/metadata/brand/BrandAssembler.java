package hovanvydut.shoplaptop.controller.v1.metadata.brand;

import hovanvydut.shoplaptop.controller.v1.api.BrandController;
import hovanvydut.shoplaptop.controller.v1.mapper.BrandDtoMapper;
import hovanvydut.shoplaptop.dto.brand.BrandDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Component
public class BrandAssembler implements RepresentationModelAssembler<BrandDto, BrandMetadata> {

    @Override
    public BrandMetadata toModel(BrandDto entity) {
        BrandMetadata brandMetadata = BrandDtoMapper.MAPPER.fromDto(entity);

        attachLink(brandMetadata);

        return brandMetadata;
    }

    public static void attachLink(BrandMetadata brandMetadata) {
        if (brandMetadata == null) return;

        brandMetadata.add(
                linkTo(methodOn(BrandController.class).getOne(brandMetadata.getId())).withSelfRel(),
                linkTo(methodOn(BrandController.class).listByPage(null, null, null, null)).withRel("all"));
    }

    @Override
    public CollectionModel<BrandMetadata> toCollectionModel(Iterable<? extends BrandDto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
