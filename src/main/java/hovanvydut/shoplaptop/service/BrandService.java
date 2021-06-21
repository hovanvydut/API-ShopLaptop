package hovanvydut.shoplaptop.service;

import hovanvydut.shoplaptop.dto.brand.BrandDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.Optional;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

public interface BrandService {

    public Page<BrandDto> listByPage(int page, int size, String keyword, String[] sort);

    BrandDto createNewBrand(@Pattern(regexp = "^\\w+(\\s\\w+)*$") String name, MultipartFile logo, Integer[] categoriesId);

    BrandDto updateBrand(Integer id, Optional<@Pattern(regexp = "^\\w+(\\s\\w+)*$") String> nameOpt,
                         Optional<MultipartFile> logoOpt, Optional<Integer[]> categoriesIdOpt);

    BrandDto findById(int id);

    void deleteOne(int id);

}
