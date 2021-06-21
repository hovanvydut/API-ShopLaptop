package hovanvydut.shoplaptop.service.impl;

import hovanvydut.shoplaptop.dto.brand.BrandDto;
import hovanvydut.shoplaptop.dto.brand.BrandMapper;
import hovanvydut.shoplaptop.exception.BrandException;
import hovanvydut.shoplaptop.exception.BrandNotFoundException;
import hovanvydut.shoplaptop.exception.ImageSizeLimitExceededException;
import hovanvydut.shoplaptop.model.Brand;
import hovanvydut.shoplaptop.model.Category;
import hovanvydut.shoplaptop.repository.BrandRepository;
import hovanvydut.shoplaptop.service.BrandService;
import hovanvydut.shoplaptop.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static hovanvydut.shoplaptop.common.constant.PaginationConstant.BRAND_PER_PAGE;
import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.BRAND_IMAGE_MAX_SIZE;
import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.BRAND_UPLOAD_DIR;
import static hovanvydut.shoplaptop.util.PagingAndSortingUtil.processSort;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Validated
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Page<BrandDto> listByPage(int page, int size, String keyword, String[] sort) {

        // set default value for
        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = BRAND_PER_PAGE;
        }

        Sort sortObj = processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        Page<Brand> pageBrand;

        if (keyword.isEmpty()) {
            pageBrand = this.brandRepository.findAll(pageable);
        } else {
            pageBrand = this.brandRepository.search(keyword, pageable);
        }

        List<Brand> brandList = pageBrand.getContent();
        List<BrandDto> brandDtoList = BrandMapper.MAPPER.from(brandList);

        return new PageImpl<>(brandDtoList, pageable, pageBrand.getTotalElements());
    }

    @Override
    public BrandDto createNewBrand(@Pattern(regexp = "^\\w+(\\s\\w+)*$") String name, MultipartFile logo,
                                   Integer[] categoriesId) {
        Brand existingBrand = this.brandRepository.findByName(name);
        if (existingBrand != null) {
            throw new BrandException("Brand name = " + name + " is existing");
        }
        Brand brand = new Brand().setName(name);

        if (categoriesId != null && categoriesId.length > 0) {
            System.out.println(categoriesId);
            for (int id : categoriesId) {
                brand.addCategory(new Category(id));
            }
        }

        if (logo == null || logo.isEmpty()) {

            // set default img
            brand.setLogo("default.jpg");

            Brand savedBrand = this.brandRepository.save(brand);

            return BrandMapper.MAPPER.from(savedBrand);

        } else {

            // image size is greater than 500kb ==> error
            checkSizeImage(logo);

            // create new category and save image
            String imageName = StringUtils.cleanPath(logo.getOriginalFilename());

            brand.setLogo(imageName);
            Brand savedBrand = this.brandRepository.save(brand);

            String uploadDir = BRAND_UPLOAD_DIR + savedBrand.getId();

            try {
                FileUploadUtil.saveFile(uploadDir, imageName, logo);
            } catch (IOException e) {
                // set default logo
                savedBrand.setLogo("default.jpg");
                savedBrand = this.brandRepository.save(savedBrand);
            }

            return BrandMapper.MAPPER.from(savedBrand);
        }

    }

    @Override
    public BrandDto updateBrand(Integer id, Optional<@Pattern(regexp = "^\\w+(\\s\\w+)*$") String> nameOpt,
                                Optional<MultipartFile> logoOpt, Optional<Integer[]> categoriesIdOpt) {

        Optional<Brand> brandOpt = this.brandRepository.findById(id);

        Brand brand = brandOpt.orElseThrow(() -> new BrandException("Brand id = " + id + " is existing"));

        nameOpt.ifPresent(name -> brand.setName(name));

        categoriesIdOpt.ifPresent(categoriesId -> {
            Set<Category> categorySet = new HashSet<>();

            for (int categoryId : categoriesId) {
                categorySet.add(new Category(categoryId));
            }

            brand.setCategories(categorySet);
        });

        if (logoOpt.isPresent()) {
            MultipartFile image = logoOpt.get();

            // image size is greater than 500kb ==> error
            checkSizeImage(image);

            // create new category and save image
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());

            brand.setLogo(imageName);
            Brand savedBrand = this.brandRepository.save(brand);

            String uploadDir = BRAND_UPLOAD_DIR + savedBrand.getId();

            try {
                FileUploadUtil.saveFile(uploadDir, imageName, image);
            } catch (IOException e) {
                savedBrand.setLogo(null);
                savedBrand = this.brandRepository.save(savedBrand);
            }

            return BrandMapper.MAPPER.from(savedBrand);
        } else {
            // set default image for category
            // category.setImage()
            Brand savedBrand = this.brandRepository.save(brand);

            return BrandMapper.MAPPER.from(savedBrand);
        }
    }

    @Override
    public BrandDto findById(int id) {
        Optional<Brand> brandOpt = this.brandRepository.findById(id);

        return brandOpt.map(BrandMapper.MAPPER::from)
                .orElseThrow(() -> new BrandNotFoundException("Brand id = " + id + " not found"));
    }

    @Override
    public void deleteOne(int id) {
        Optional<Brand> brandOpt = this.brandRepository.findById(id);

        Brand brand = brandOpt.orElseThrow(() -> new BrandNotFoundException("Brand id = " + id + " not found"));

        this.brandRepository.delete(brand);
    }

    private void checkSizeImage(MultipartFile file) {
        if (file.getSize() > BRAND_IMAGE_MAX_SIZE) {
            throw new ImageSizeLimitExceededException("File size is greater than " + BRAND_IMAGE_MAX_SIZE);
        }
    }
}
