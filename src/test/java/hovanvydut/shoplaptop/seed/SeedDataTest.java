package hovanvydut.shoplaptop.seed;

import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class SeedDataTest {

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void seedRoles() {
        Role adminRole = new Role("Admin","Manage everything");
        Role editorRole = new Role("Editor", "Manage categories, brands, products, articles, and menu");
        Role salespersonRole = new Role("Salesperson", "Manage product price, customer, shipping, orders and sale reports");
        Role shipperRole = new Role("Shipper", "View products, view orders and update order status");
        Role assistanceRole = new Role("Assistance", "Manage questions and reviews");

        this.roleRepo.saveAll(List.of(adminRole, editorRole, salespersonRole, shipperRole, assistanceRole));
    }
}
