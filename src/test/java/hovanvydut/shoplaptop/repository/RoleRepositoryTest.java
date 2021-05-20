package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void createFirstRole() {
        Role role = new Role("Admin","Manage everything");
        Role savedRole = this.roleRepo.save(role);

        Assertions.assertTrue(savedRole.getId() > 0);
    }

}
