package hovanvydut.shoplaptop.controller.v1.api;

import hovanvydut.shoplaptop.controller.v1.mapper.ProductDtoMapper;
import hovanvydut.shoplaptop.controller.v1.metadata.product.ProductAssembler;
import hovanvydut.shoplaptop.controller.v1.metadata.product.ProductMetadata;
import hovanvydut.shoplaptop.controller.v1.request.product.CreateProductRequest;
import hovanvydut.shoplaptop.controller.v1.request.product.UpdateProductRequest;
import hovanvydut.shoplaptop.dto.product.CreateProductDto;
import hovanvydut.shoplaptop.dto.product.ProductDto;
import hovanvydut.shoplaptop.dto.product.UpdateProductDto;
import hovanvydut.shoplaptop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.PRODUCTS_PER_PAGE;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Validated
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    private final PagedResourcesAssembler<ProductDto> pagedResourcesAssembler;

    private final ProductAssembler productAssembler;

    public ProductController(ProductService productService,
                             PagedResourcesAssembler pagedResourcesAssembler,
                             ProductAssembler productAssembler) {

        this.productService = productService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.productAssembler = productAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<ProductMetadata>> listByPage(@RequestParam(required = false) Optional<String> keyword,
                                                                  @RequestParam(required = false) Optional<Integer> page,
                                                                  @RequestParam(required = false) Optional<Integer> size,
                                                                  @RequestParam(defaultValue = "id,asc") String[] sort) {

        Page<ProductDto> productDtoPage = this.productService
                .getAll(page.orElse(1), size.orElse(PRODUCTS_PER_PAGE), keyword.orElse(""), sort);

        PagedModel<ProductMetadata> pagedModel = this.pagedResourcesAssembler.toModel(productDtoPage, this.productAssembler);

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductMetadata> getOne(@PathVariable("id") int id) {

        ProductDto productDto = this.productService.findById(id);

        return ResponseEntity.ok(this.productAssembler.toModel(productDto));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createNewProduct(@Valid @ModelAttribute CreateProductRequest createProductRequest) throws IOException {

        // check name existing???
        System.out.println(createProductRequest.getMainImage().getOriginalFilename());

        CreateProductDto dto = ProductDtoMapper.MAPPER.to(createProductRequest);
        ProductDto productDto = this.productService.createNewProduct(dto);

        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @Valid @ModelAttribute UpdateProductRequest updateProductRequest) throws IOException {

        UpdateProductDto dto = ProductDtoMapper.MAPPER.to(updateProductRequest);
        ProductDto productDto = this.productService.updateProduct(id, dto);

        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable int id) {
        this.productService.deleteById(id);
    }

}
