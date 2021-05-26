package hovanvydut.shoplaptop.seed;

import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.model.User;
import hovanvydut.shoplaptop.repository.RoleRepository;
import hovanvydut.shoplaptop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Seeding data")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Rollback(false)
public class SeedDataTest {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    @Order(1)
    @DisplayName("Seeding Role")
    public void seedRoles() {
        Role adminRole = new Role("Admin","Manage everything");
        Role editorRole = new Role("Editor", "Manage categories, brands, products, articles, and menu");
        Role salespersonRole = new Role("Salesperson", "Manage product price, customer, shipping, orders and sale reports");
        Role shipperRole = new Role("Shipper", "View products, view orders and update order status");
        Role assistanceRole = new Role("Assistance", "Manage questions and reviews");

        this.roleRepo.saveAll(List.of(adminRole, editorRole, salespersonRole, shipperRole, assistanceRole));
    }

    @Test
    @Order(2)
    @DisplayName("Seeding admin user")
    public void seedAdminUser() {
        User admin = new User("adminadmin@gmail.com", "123123", "Admin", "admin");
        User editor = new User("editor@gmail.com", "123123", "Editor", "editor");
        User sales = new User("sales@gmail.com", "123123", "Sales", "sales");
        User shipper = new User("shipper@gmail.com", "123123", "Shipper", "shipper");

        Optional<Role> adminRole = this.roleRepo.findById(1);
        Optional<Role> editorRole = this.roleRepo.findById(2);
        Optional<Role> salesRole = this.roleRepo.findById(3);
        Optional<Role> shipperRole = this.roleRepo.findById(4);

        adminRole.ifPresent(admin::addRole);
        editorRole.ifPresent(editor::addRole);
        salesRole.ifPresent(sales::addRole);
        shipperRole.ifPresent(shipper::addRole);

        User savedUser = this.userRepo.save(admin);
        this.userRepo.saveAll(List.of(editor, sales, shipper));
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
