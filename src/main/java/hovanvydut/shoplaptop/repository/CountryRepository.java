package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
