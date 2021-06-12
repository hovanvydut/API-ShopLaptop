package hovanvydut.shoplaptop.util;

import hovanvydut.shoplaptop.model.Product;
import hovanvydut.shoplaptop.model.ProductImage;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static hovanvydut.shoplaptop.common.constant.UploadImageConstant.*;

/**
 * @author hovanvydut
 * Created on 6/12/21
 */

public class ProductSaverUtil {

    public static void setMainImageName(MultipartFile mainImageMultipart, Product product) {
        if (!mainImageMultipart.isEmpty()) {
            String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
            product.setMainImage(fileName);
        }
    }

    public static void setNewExtraImageNames(MultipartFile[] extraImageMultiparts, Product product) {
        if (extraImageMultiparts != null && extraImageMultiparts.length > 0) {

            // delete all previous images
            product.getImages().clear();

            for (MultipartFile multipartFile : extraImageMultiparts) {
                if (!multipartFile.isEmpty()) {
                    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                    product.addExtraImage(fileName);
                }
            }
        }
    }

    public static void saveUploadedImages(MultipartFile mainImageMultipart,
                                   MultipartFile[] extraImageMultiparts, Product savedProduct) throws IOException {
        if (mainImageMultipart != null && !mainImageMultipart.isEmpty()) {

            String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
            String uploadDir = PRODUCT_UPLOAD_DIR + savedProduct.getId();

            // delete all previous images in folder
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, mainImageMultipart);
        }

        if (extraImageMultiparts!= null && extraImageMultiparts.length > 0) {
            String uploadDir = PRODUCT_UPLOAD_DIR + savedProduct.getId() + "/extras";

            // delete all previous images in folder
            FileUploadUtil.cleanDir(uploadDir);
            for (MultipartFile multipartFile : extraImageMultiparts) {
                if (multipartFile.isEmpty()) continue;

                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }

        }

    }

    public static void setProductDetails(String[] detailNames,
                                  String[] detailValues, Product product) {
        if (detailNames == null || detailNames.length == 0) return;

        // delete all previous details
        product.getDetails().clear();

        for (int count = 0; count < detailNames.length; count++) {
            String name = detailNames[count];
            String value = detailValues[count];

            product.addDetail(name, value);
        }
    }
}
