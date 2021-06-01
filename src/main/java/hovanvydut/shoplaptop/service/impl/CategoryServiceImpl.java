package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.common.CategoryPageInfo;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import hovanvydut.shoplaptop.dto.category.CategoryMapper;
import hovanvydut.shoplaptop.exception.CategoryNotFoundException;
import hovanvydut.shoplaptop.model.Category;
import hovanvydut.shoplaptop.repository.CategoryRepository;
import hovanvydut.shoplaptop.service.CategoryService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

@Validated
@Service
public class CategoryServiceImpl implements CategoryService {

    private final int CATEGORIES_PER_PAGE = 2;

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryDto> listByPage(int pageNum, int pageSize, String sortDir, String keyword) {

        // set default value for
        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = CATEGORIES_PER_PAGE;
        }

        if (sortDir == null || sortDir.isEmpty()) {
            sortDir = "asc";
        }

        Sort sort = Sort.by("name");
        if (sortDir.equals("asc")) {
            sort = sort.ascending();
        } else if (sortDir.equals("desc")) {
            sort = sort.descending();
        }
        System.out.println(pageNum + " " +  pageSize);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<Category> pageCategory = null;

        if (keyword != null && !keyword.isEmpty()) {
            pageCategory = this.categoryRepository.search(keyword, pageable);
        } else {
            pageCategory = this.categoryRepository.findRootCategories(pageable);
        }

        List<Category> rootCategories = pageCategory.getContent();
        List<CategoryDto> categoryDtoList = CategoryMapper.MAPPER.fromCategoryList(rootCategories);
        System.out.println(rootCategories);
        Page<CategoryDto> pageCategoryDto = new PageImpl<>(categoryDtoList, pageable, categoryDtoList.size());

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
}
