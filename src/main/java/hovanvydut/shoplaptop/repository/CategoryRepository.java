package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * Created on 5/31/21
 */

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    Category findByName(String name);

    Category findBySlug(String slug);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    public Page<Category> search(String keyword, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.parent is NULL")
    public Page<Category> findRootCategories(Pageable pageable);
}
