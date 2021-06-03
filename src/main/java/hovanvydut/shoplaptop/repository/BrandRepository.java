package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {

}
