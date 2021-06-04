package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryMetadata;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Optional;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Validated
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final int CATEGORIES_PER_PAGE = 2;

    private final CategoryService categoryService;

    private final PagedResourcesAssembler<CategoryDto> pagedResourcesAssembler;

    private final CategoryAssembler categoryAssembler;

    public CategoryController(CategoryService categoryService,
                              PagedResourcesAssembler<CategoryDto> pagedResourcesAssembler,
                              CategoryAssembler categoryAssembler) {
        this.categoryService = categoryService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.categoryAssembler = categoryAssembler;
    }

    @GetMapping()
    public ResponseEntity<PagedModel<CategoryMetadata>> listByPage(@RequestParam(required = false) Optional<String> keyword,
                                                                   @RequestParam(required = false) Optional<Integer> page,
                                                                   @RequestParam(required = false) Optional<Integer> size,
                                                                   @RequestParam(defaultValue = "id,desc") String[] sort) {

        Page<CategoryDto> paged = this.categoryService
                .listByPage(page.orElse(1), size.orElse(CATEGORIES_PER_PAGE), keyword.orElse(""), sort);

        PagedModel<CategoryMetadata> model = this.pagedResourcesAssembler.toModel(paged, categoryAssembler);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryMetadata> getOne(@PathVariable int id) {

        CategoryDto categoryDto = this.categoryService.findById(id);

        return ResponseEntity.ok(this.categoryAssembler.toModel(categoryDto));
    }

    @PostMapping()
    public ResponseEntity<CategoryMetadata> createNewCategory(@Valid @RequestParam(required = false) MultipartFile image,
                                                              @Pattern(regexp = "^\\w+(\\s\\w+)*$") @RequestParam String name,
                                                              @RequestParam boolean enabled,
                                                              @Pattern(regexp = "^\\w+(\\-\\w+)*$") @RequestParam String slug,
                                                              @Positive @RequestParam(required = false) Integer parentId) {

        CategoryDto categoryDto = this.categoryService.createNewCategory(name, slug, Optional.ofNullable(image), enabled, parentId);

        return ResponseEntity.ok(this.categoryAssembler.toModel(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryMetadata> updateCategory(@PathVariable("id") int categoryId,
                               @Valid @RequestParam(required = false) MultipartFile image,
                               @Pattern(regexp = "^\\w+(\\s\\w+)*$") @RequestParam(required = false) String name,
                               @RequestParam(required = false) boolean enabled,
                               @Pattern(regexp = "^\\w+(\\-\\w+)*$") @RequestParam(required = false) String slug,
                               @Positive @RequestParam(required = false) Integer parentId) {

        CategoryDto categoryDto = this.categoryService.updateCategory(categoryId, Optional.ofNullable(name), Optional.ofNullable(slug),
                Optional.ofNullable(image), Optional.ofNullable(enabled), parentId);

        return ResponseEntity.ok(this.categoryAssembler.toModel(categoryDto));
    }

    // valid id is number
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable int id) {
        this.categoryService.deleteOne(id);
    }

}
