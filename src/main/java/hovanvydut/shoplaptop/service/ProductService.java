package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.product.CreateProductDto;
import hovanvydut.shoplaptop.dto.product.ProductDto;
import hovanvydut.shoplaptop.dto.product.UpdateProductDto;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author hovanvydut
 * Created on 6/7/21
 */

public interface ProductService {

    Page<ProductDto> getAll(int page, int size, String keyword, String[] sort);

    ProductDto findById(int id);

    ProductDto createNewProduct(@Valid CreateProductDto createProductDto) throws IOException;

    ProductDto updateProduct(int id, @Valid UpdateProductDto updateProductDto) throws IOException;

    void deleteById(int id);
}
