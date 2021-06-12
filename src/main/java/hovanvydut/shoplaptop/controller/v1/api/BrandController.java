package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.mapper.BrandDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.brand.BrandAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.brand.BrandMetadata;
import hovanvydut.shoplaptop.dto.brand.BrandDto;
import hovanvydut.shoplaptop.dto.brand.BrandMapper;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.Optional;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Validated
@Controller
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final int BRAND_PER_PAGE = 2;

    private final BrandService brandService;

    private final PagedResourcesAssembler<BrandDto> pagedResourcesAssembler;

    private final BrandAssembler brandAssembler;

    public BrandController(BrandService brandService,
                           PagedResourcesAssembler<BrandDto> pagedResourcesAssembler,
                           BrandAssembler brandAssembler) {

        this.brandService = brandService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.brandAssembler = brandAssembler;

    }

    @GetMapping()
    public ResponseEntity<PagedModel<BrandMetadata>> listByPage(@RequestParam(required = false) Optional<String> keyword,
                                                                @RequestParam(required = false) Optional<Integer> page,
                                                                @RequestParam(required = false) Optional<Integer> size,
                                                                @RequestParam(required = false, defaultValue = "id,asc") String[] sort) {

        Page<BrandDto> pageDto = this.brandService.listByPage(page.orElse(1), size.orElse(BRAND_PER_PAGE), keyword.orElse(""), sort);
        PagedModel<BrandMetadata> pagedModel = this.pagedResourcesAssembler.toModel(pageDto, this.brandAssembler);

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandMetadata> getOne(@PathVariable int id) {
        BrandMetadata brandMetadata = BrandDtoMapper.MAPPER.fromDto(this.brandService.findById(id));
        return ResponseEntity.ok(brandMetadata);
    }

    @PostMapping()
    public ResponseEntity<BrandMetadata> createNewBrand(@Pattern(regexp = "^\\w+(\\s\\w+)*$") String name,
                                                        MultipartFile logo,
                                                        Integer[] categoriesId) {
        System.out.println(categoriesId);
        BrandDto brandDto = this.brandService.createNewBrand(name, logo, categoriesId);

        return ResponseEntity.ok(BrandDtoMapper.MAPPER.fromDto(brandDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandMetadata> updateBrand(@PathVariable("id") int id,
                                                     @Pattern(regexp = "^\\w+(\\s\\w+)*$") @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) MultipartFile logo,
                                                     @RequestParam(required = false) Integer[] categoriesId) {

        BrandDto brandDto = this.brandService.updateBrand(id, Optional.ofNullable(name), Optional.ofNullable(logo), Optional.ofNullable(categoriesId));

        return ResponseEntity.ok(BrandDtoMapper.MAPPER.fromDto(brandDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable("id") int id) {
        this.brandService.deleteOne(id);

        return ResponseEntity.ok("Deleted Brand id = " + id);
    }

}
