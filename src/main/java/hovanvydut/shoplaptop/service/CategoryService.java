package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.common.CategoryPageInfo;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

public interface CategoryService {

    Page<CategoryDto> listByPage(int pageNum, int pageSize, String sortDir, String keyword);

    CategoryDto findById(int id);

    void deleteOne(int id);
}
