package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.State;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * Created on 6/21/21
 */

@Repository
public interface StateRepository extends PagingAndSortingRepository<State, Integer> {
}
