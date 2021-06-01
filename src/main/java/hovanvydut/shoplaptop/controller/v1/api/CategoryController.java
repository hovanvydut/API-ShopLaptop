package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.common.CategoryPageInfo;
import hovanvydut.shoplaptop.controller.v1.mapper.CategoryDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.category.CategoryMetadata;
import hovanvydut.shoplaptop.controller.v1.request.category.CreateCategoryRequest;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.model.Category;
import hovanvydut.shoplaptop.service.CategoryService;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
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
    public ResponseEntity<PagedModel<CategoryMetadata>> listByPage(@RequestParam(required = false) Optional<String> sortDir,
                                                                   @RequestParam(required = false) Optional<String> keyword,
                                                                   @RequestParam(required = false) Optional<Integer> page,
                                                                   @RequestParam(required = false) Optional<Integer> pageSize) {

        Page<CategoryDto> paged = this.categoryService.listByPage(page.orElse(1), pageSize.orElse(CATEGORIES_PER_PAGE), sortDir.orElse(null), keyword.orElse(null));

        PagedModel<CategoryMetadata> model = this.pagedResourcesAssembler.toModel(paged, categoryAssembler);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryMetadata> getOne(@PathVariable int id) {

        CategoryDto categoryDto = this.categoryService.findById(id);

        return ResponseEntity.ok(this.categoryAssembler.toModel(categoryDto));
    }

    @PostMapping()
    public void createNewCategory(@Valid @RequestParam MultipartFile image,
                                  @Length(min = 1, max = 128) @RequestParam String name,
                                  @RequestParam boolean enabled,
                                  @Min(1) @RequestParam int parentId) {
        System.out.println(name);
        System.out.println(enabled);
        System.out.println(parentId);
    }

    // valid id is number
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable int id) {
        this.categoryService.deleteOne(id);
    }

}
