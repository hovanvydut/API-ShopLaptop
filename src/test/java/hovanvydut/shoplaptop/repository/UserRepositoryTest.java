package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @Test
    @DisplayName("Test insert new record into User entity")
    public void testInsertNewRecord() {
        User user = new User("hovanvydut@gmail.com", "123123123", "Vy", "Ho Van");
        User userInDB = this.userRepo.save(user);

        Assertions.assertTrue(userInDB.getId() > 0);
    }

}
