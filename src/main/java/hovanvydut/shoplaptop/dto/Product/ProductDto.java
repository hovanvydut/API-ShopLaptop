package hovanvydut.shoplaptop.dto.Product;

import hovanvydut.shoplaptop.dto.brand.BrandDto;
import hovanvydut.shoplaptop.dto.category.CategoryDto;

import java.util.Date;

/**
 * @author hovanvydut
 * Created on 6/7/21
 */

public class ProductDto {
    private Integer id;

    private String name;

    private String slug;

    private String shortDescription;

    private String fullDescription;

    private Date createdTime;

    private Date updatedTime;

    private boolean enabled;

    private boolean inStock;

    private float cost;

    private float price;

    private float discountPercent;

    private float length;

    private float width;

    private float height;

    private float weight;

    private CategoryDto category;

    private BrandDto brand;
}
