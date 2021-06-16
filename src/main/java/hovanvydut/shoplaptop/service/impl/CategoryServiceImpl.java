package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.dto.category.CategoryMapper;
import hovanvydut.shoplaptop.exception.CategoryException;
import hovanvydut.shoplaptop.exception.CategoryNotFoundException;
import hovanvydut.shoplaptop.exception.ImageSizeLimitExceededException;
import hovanvydut.shoplaptop.model.Category;
import hovanvydut.shoplaptop.repository.CategoryRepository;
import hovanvydut.shoplaptop.service.BrandService;
import hovanvydut.shoplaptop.service.CategoryService;
import hovanvydut.shoplaptop.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.CATEGORIES_PER_PAGE;
import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.CATEGORY_IMAGE_MAX_SIZE;
import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.CATEGORY_UPLOAD_DIR;
import static hovanvydut.shoplaptop.util.PagingAndSortingUtil.processSort;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Validated
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryDto> listByPage(int page, int size, String keyword, String[] sort) {

        // set default value for
        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = CATEGORIES_PER_PAGE;
        }

        Sort sortObj = processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        Page<Category> pageCategory = null;

        if (keyword != null && !keyword.isEmpty()) {
            pageCategory = this.categoryRepository.search(keyword, pageable);
        } else {
            pageCategory = this.categoryRepository.findRootCategories(pageable);
        }

        List<Category> rootCategories = pageCategory.getContent();
        List<CategoryDto> categoryDtoList = CategoryMapper.MAPPER.fromCategoryList(rootCategories);

        System.out.println(rootCategories);

        Page<CategoryDto> pageCategoryDto = new PageImpl<>(categoryDtoList, pageable, pageCategory.getTotalElements());

        return pageCategoryDto;
    }

    @Override
    public CategoryDto findById(int id) {
        Optional<Category> categoryOpt = this.categoryRepository.findById(id);

        return categoryOpt.map(CategoryMapper.MAPPER::fromCategory).orElseThrow(() -> new CategoryNotFoundException());
    }

    @Override
    public void deleteOne(int id) {
        Optional<Category> categoryOpt = this.categoryRepository.findById(id);

        if (categoryOpt.isEmpty()) {
            throw new CategoryNotFoundException();
        } else {
            this.categoryRepository.delete(categoryOpt.get());
        }

    }

    @Override
    public CategoryDto  createNewCategory(@Pattern(regexp = "^\\w+(\\s\\w+)*$") String name,
                                  @Pattern(regexp = "^\\w+(\\-\\w+)*$") String slug,
                                  Optional<MultipartFile> imageOpt,
                                  boolean enabled,
                                  @Positive Integer parentId) {
        // check name, slug unique
        if (this.categoryRepository.findByName(name) != null) throw new CategoryException(name + " name is existing");
        if (this.categoryRepository.findBySlug(slug) != null) throw new CategoryException(slug + " slug is existing");

        Category category = new Category()
                .setName(name)
                .setSlug(slug)
                .setEnabled(enabled);

        if (parentId != null) {
            category.setParent(new Category(parentId));
        }

        if (imageOpt.isPresent()) {
            MultipartFile image = imageOpt.get();



            // image size is greater than 500kb ==> error
            if (image.getSize() > CATEGORY_IMAGE_MAX_SIZE) {
                throw new ImageSizeLimitExceededException("File size is greater than " + CATEGORY_IMAGE_MAX_SIZE);
            }

            // create new category and save image
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());

            category.setImage(imageName);
            Category savedCategory = this.categoryRepository.save(category);

            String uploadDir = CATEGORY_UPLOAD_DIR + savedCategory.getId();

            try {
                FileUploadUtil.saveFile(uploadDir, imageName, image);
            } catch (IOException e) {
                savedCategory.setImage(null);
                savedCategory = this.categoryRepository.save(savedCategory);
            }

            return CategoryMapper.MAPPER.fromCategory(savedCategory);
        } else {
            // set default image for category
            // category.setImage()
            Category savedCategory = this.categoryRepository.save(category);

            return CategoryMapper.MAPPER.fromCategory(savedCategory);
        }
    }

    @Override
    public CategoryDto  updateCategory(int categoryId,
                                       Optional<@Pattern(regexp = "^\\w+(\\s\\w+)*$") String> name,
                                       Optional<@Pattern(regexp = "^\\w+(\\-\\w+)*$") String> slug,
                                       Optional<MultipartFile> imageOpt,
                                       Optional<Boolean> enabled,
                                       @Positive Integer parentId) {

        Optional<Category> currentCategoryOpt = this.categoryRepository.findById(categoryId);

        Category currentCategory = currentCategoryOpt
                .orElseThrow(() -> new CategoryException("Category with id=" + categoryId + " is existing"));

        name.ifPresent(value -> currentCategory.setName(value));
        slug.ifPresent(value -> currentCategory.setSlug(value));
        enabled.ifPresent(value -> currentCategory.setEnabled(value));
        if (parentId != null) currentCategory.setParent(new Category(parentId));

        if (imageOpt.isPresent()) {
            MultipartFile image = imageOpt.get();

            final String baseUploadDir = "src/main/resources/static/img/category-images/";
            String uploadDir = baseUploadDir + currentCategory.getId();

            // image size is greater than 500kb ==> error
            if (image.getSize() > CATEGORY_IMAGE_MAX_SIZE) {
                throw new ImageSizeLimitExceededException("File size is greater than " + CATEGORY_IMAGE_MAX_SIZE);
            }

            // clear all old image, upload file and then update category
            FileUploadUtil.cleanDir(uploadDir);

            try {
                String imageName = StringUtils.cleanPath(image.getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, imageName, image);

                currentCategory.setImage(imageName);
            } catch (IOException e) {
                throw new CategoryException("Upload file failed!");
            }
        }

        Category savedCategory = this.categoryRepository.save(currentCategory);

        return CategoryMapper.MAPPER.fromCategory(savedCategory);
    }


}
