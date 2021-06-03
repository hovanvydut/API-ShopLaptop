package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.common.CategoryPageInfo;
import hovanvydut.shoplaptop.dto.category.CategoryDto;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

/**
 * @author hovanvydut
 * Created on 6/1/21
 */

public interface CategoryService {

    Page<CategoryDto> listByPage(int pageNum, int size, String keyword, String[] sort);

    CategoryDto findById(int id);

    void deleteOne(int id);

    public CategoryDto  createNewCategory(@Pattern(regexp = "^\\w+(\\s\\w+)*$") String name,
                                          @Pattern(regexp = "^\\w+(\\-\\w+)*$") String slug,
                                          Optional<MultipartFile> imageOpt,
                                          boolean enabled,
                                          @Positive Integer parentId);

    public CategoryDto  updateCategory(int categoryId,
                                       Optional<@Pattern(regexp = "^\\w+(\\s\\w+)*$") String> name,
                                       Optional<@Pattern(regexp = "^\\w+(\\-\\w+)*$") String> slug,
                                       Optional<MultipartFile> imageOpt,
                                       Optional<Boolean> enabled,
                                       @Positive Integer parentId);
}
