package hovanvydut.shoplaptop.seed;

import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.model.User;
import hovanvydut.shoplaptop.repository.RoleRepository;
import hovanvydut.shoplaptop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Optional;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class SeedDataTest {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @BeforeAll
    public void init() {
        // clear all database
    }

    @Test
    @DisplayName("Seeding Role")
    @Order(1)
    public void seedRoles() {
        Role adminRole = new Role("Admin","Manage everything");
        Role editorRole = new Role("Editor", "Manage categories, brands, products, articles, and menu");
        Role salespersonRole = new Role("Salesperson", "Manage product price, customer, shipping, orders and sale reports");
        Role shipperRole = new Role("Shipper", "View products, view orders and update order status");
        Role assistanceRole = new Role("Assistance", "Manage questions and reviews");

        this.roleRepo.saveAll(List.of(adminRole, editorRole, salespersonRole, shipperRole, assistanceRole));
    }

    @Test
    @DisplayName("Seeding admin user")
    @Order(2)
    public void seedAdminUser() {
        User admin = new User("adminadmin@gmail.com", "123123123", "Admin", "Admin");
        Optional<Role> roleOpt = this.roleRepo.findById(1);

        roleOpt.ifPresent(role -> admin.addRole(role));

        User savedUser = this.userRepo.save(admin);
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
