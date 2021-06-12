package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * Created on 6/10/21
 */

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    Page<Product> search(String keyword, Pageable pageable);

}
