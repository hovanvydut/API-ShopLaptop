package hovanvydut.shoplaptop.service.impl;

import com.github.slugify.Slugify;
import hovanvydut.shoplaptop.dto.product.CreateProductDto;
import hovanvydut.shoplaptop.dto.product.ProductDto;
import hovanvydut.shoplaptop.dto.product.ProductMapper;
import hovanvydut.shoplaptop.dto.product.UpdateProductDto;
import hovanvydut.shoplaptop.exception.ProductNotFoundException;
import hovanvydut.shoplaptop.model.Brand;
import hovanvydut.shoplaptop.model.Category;
import hovanvydut.shoplaptop.model.Product;
import hovanvydut.shoplaptop.repository.ProductRepository;
import hovanvydut.shoplaptop.service.ProductService;
import hovanvydut.shoplaptop.util.FileUploadUtil;
import hovanvydut.shoplaptop.util.ProductSaverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.PRODUCTS_PER_PAGE;
import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.PRODUCT_UPLOAD_DIR;
import static hovanvydut.shoplaptop.util.PagingAndSortingUtil.processSort;

/**
 * @author hovanvydut
 * Created on 6/7/21
 */

@Validated
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> getAll(int page, int size, String keyword, String[] sort) {

        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = PRODUCTS_PER_PAGE;
        }

        Sort sortObj = processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        Page<Product> pageProduct;

        if (keyword != null || !keyword.isEmpty()) {
            pageProduct = this.productRepository.search(keyword, pageable);
        } else {
            pageProduct = this.productRepository.findAll(pageable);
        }

        List<Product> listProduct = pageProduct.getContent();
        List<ProductDto> listProductDto = ProductMapper.MAPPER.fromProduct(listProduct);

        return new PageImpl<>(listProductDto, pageable, pageProduct.getTotalElements());
    }

    @Override
    public ProductDto findById(int id) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        return productOpt.map(ProductMapper.MAPPER::fromProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product id = " + id + " not found"));
    }

    @Override
    public ProductDto createNewProduct(@Valid CreateProductDto createProductDto) throws IOException {
        if (createProductDto == null) {
            return null;
        }

        Product product = new Product();
        product.setName(createProductDto.getName())
                .setShortDescription(createProductDto.getShortDescription())
                .setFullDescription(createProductDto.getFullDescription())
                .setCreatedTime(new Date())
                .setEnabled(createProductDto.isEnabled())
                .setInStock(createProductDto.isInStock())
                .setCost(createProductDto.getCost())
                .setPrice(createProductDto.getPrice())
                .setDiscountPercent(createProductDto.getDiscountPercent())
                .setLength(createProductDto.getLength())
                .setWidth(createProductDto.getWidth())
                .setWeight(createProductDto.getWeight())
                .setHeight(createProductDto.getHeight());

        if (createProductDto.getBrandId() > 0) {
            product.setBrand(new Brand().setId(createProductDto.getBrandId()));
        }

        if (createProductDto.getCategoryId() > 0) {
            product.setCategory(new Category().setId(createProductDto.getCategoryId()));
        }

        if (createProductDto.getSlug() == null || createProductDto.getSlug().isEmpty()) {
            String name = product.getName();
            String slug = new Slugify().slugify(name);
            product.setSlug(slug);
        }

        ProductSaverUtil.setProductDetails(createProductDto.getDetailNames(), createProductDto.getDetailValues(), product);
        ProductSaverUtil.setMainImageName(createProductDto.getMainImage(), product);
        ProductSaverUtil.setNewExtraImageNames(createProductDto.getExtraImages(), product);

        Product savedProduct = this.productRepository.save(product);

        ProductSaverUtil.saveUploadedImages(createProductDto.getMainImage(), createProductDto.getExtraImages(), savedProduct);

        return ProductMapper.MAPPER.fromProduct(savedProduct);
    }

    @Override
    public ProductDto updateProduct(int id, UpdateProductDto updateProductDto) throws IOException {
        Optional<Product> productOpt = this.productRepository.findById(id);

        Product product = productOpt.orElseThrow(() -> new ProductNotFoundException("Product id = " + id + " not found"));
        product.setName(updateProductDto.getName())
                .setShortDescription(updateProductDto.getShortDescription())
                .setFullDescription(updateProductDto.getFullDescription())
                .setUpdatedTime(new Date())
                .setEnabled(updateProductDto.isEnabled())
                .setInStock(updateProductDto.isInStock())
                .setCost(updateProductDto.getCost())
                .setPrice(updateProductDto.getPrice())
                .setDiscountPercent(updateProductDto.getDiscountPercent())
                .setLength(updateProductDto.getLength())
                .setWidth(updateProductDto.getWidth())
                .setWeight(updateProductDto.getWeight())
                .setHeight(updateProductDto.getHeight());

        if (updateProductDto.getBrandId() > 0) {
            product.setBrand(new Brand().setId(updateProductDto.getBrandId()));
        }

        if (updateProductDto.getCategoryId() > 0) {
            product.setCategory(new Category().setId(updateProductDto.getCategoryId()));
        }

        if (updateProductDto.getSlug() == null || updateProductDto.getSlug().isEmpty()) {
            String name = product.getName();
            String slug = new Slugify().slugify(name);
            product.setSlug(slug);
        }

        ProductSaverUtil.setProductDetails(updateProductDto.getDetailNames(), updateProductDto.getDetailValues(), product);
        ProductSaverUtil.setMainImageName(updateProductDto.getMainImage(), product);
        ProductSaverUtil.setNewExtraImageNames(updateProductDto.getExtraImages(), product);

        Product savedProduct = this.productRepository.save(product);

        ProductSaverUtil.saveUploadedImages(updateProductDto.getMainImage(), updateProductDto.getExtraImages(), savedProduct);

        return ProductMapper.MAPPER.fromProduct(savedProduct);
    }

    @Override
    public void deleteById(int id) {
        Optional<Product> productOpt = this.productRepository.findById(id);

        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("Product id = " + id + " not found");
        } else {
            this.productRepository.delete(productOpt.get());
            FileUploadUtil.removeDir(PRODUCT_UPLOAD_DIR + id);
            FileUploadUtil.removeDir(PRODUCT_UPLOAD_DIR + id + "/extras");
        }
    }

}
