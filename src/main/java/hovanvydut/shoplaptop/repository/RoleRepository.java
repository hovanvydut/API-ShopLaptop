package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hovanvydut
 * @created 5/25/21
 */

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.name LIKE %?1%")
    Page<Role> search (String keyword, Pageable pageable);

}
