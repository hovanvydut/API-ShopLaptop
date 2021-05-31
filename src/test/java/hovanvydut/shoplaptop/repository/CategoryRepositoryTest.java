package hovanvydut.shoplaptop.repository;

import hovanvydut.shoplaptop.model.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 5/31/21
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Create root category")
    @Order(1)
    public void testCreateRootCategory() {
        Category computerCategory = new Category("Computer");
        Category electronicCategory = new Category("Electronics");

        Category savedCategory = this.categoryRepository.save(computerCategory);
        this.categoryRepository.save(electronicCategory);

        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Create sub category")
    @Order(2)
    public void testCreateSubCategory() {
        Category computerCategory = this.categoryRepository.findById(1).get();

        Category subCategory = new Category("Desktops", computerCategory);

        Category savedCategory = this.categoryRepository.save(subCategory);

        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testAddMoreCategory1() {
        Category parent = this.categoryRepository.findById(1).get();

        Category laptop = new Category("Laptops", parent);
        Category components = new Category("Computer Components", parent);

        this.categoryRepository.saveAll(List.of(laptop, components));
    }

    @Test
    @Order(4)
    public void testAddMoreCategory2() {
        Category parent = this.categoryRepository.findById(2).get();

        Category cameras = new Category("Cameras", parent);
        Category smartphones = new Category("Smartphones", parent);

        this.categoryRepository.saveAll(List.of(cameras, smartphones));
    }

    @Test
    @Order(5)
    public void testAddMoreCategory3() {
        Category parent = this.categoryRepository.findById(5).get();

        Category memory = new Category("Memory", parent);

        this.categoryRepository.save(memory);
    }

    @Test
    @Order(6)
    public void testGetCategory() {
        Category category = this.categoryRepository.findById(1).get();

        Set<Category> categorySet = category.getChildren();

        for (Category subcategory : categorySet) {
            System.out.println(subcategory.getName());
        }

        Assertions.assertThat(categorySet.size()).isGreaterThan(0);
    }

}
