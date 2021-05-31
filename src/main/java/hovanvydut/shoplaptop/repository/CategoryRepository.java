package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author hovanvydut
 * Created on 5/31/21
 */

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}
