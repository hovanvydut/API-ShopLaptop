package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {

    public Brand findByName(String name);
    @Query("SELECT b FROM Brand b WHERE b.name LIKE %?1%")
    public Page<Brand> search(String keyword, Pageable pageable);

}
