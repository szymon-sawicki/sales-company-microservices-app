package com.salescompany.productservice.infrastructure.persistence;

import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.persistence.dao.ProducerEntityDao;
import com.salescompany.productservice.infrastructure.persistence.dao.ProductEntityDao;
import com.salescompany.productservice.infrastructure.persistence.entity.ProducerEntity;
import com.salescompany.productservice.infrastructure.persistence.entity.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductEntityDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductEntityDao productEntityDao;


    @Test
    @DisplayName("when product is searched by id")
    public void test1() {

        var id = 1L;

        var name1 = "Testotron";
        var name2 = "Testomania";

        var user1 = ProductEntity.builder().name(name1).build();
        var user2 = ProductEntity.builder().name(name2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(productEntityDao.findById(id).orElseThrow().getName())
                .isEqualTo("Testotron");
    }

    @Test
    @DisplayName("when product is searched by name")
    public void test2() {

        var name1 = "Testotron";
        var name2 = "Testomania";

        var user1 = ProductEntity.builder().name(name1).price(BigDecimal.TEN).build();
        var user2 = ProductEntity.builder().name(name2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(productEntityDao.findByName("Testotron").orElseThrow().getPrice())
                .isEqualTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("when product is updated")
    public void test3() {

        var name1 = "Testotron";
        var name2 = "Testomania";

        var user1 = ProductEntity.builder().name(name1).price(BigDecimal.TEN).build();

        testEntityManager.persist(user1);
        testEntityManager.flush();

        var id = productEntityDao.findByName(name1).orElseThrow().getId();
        var userUpdate = ProductEntity.builder().id(id).name(name2).build();

        assertThat(productEntityDao.save(userUpdate).getName())
                .isEqualTo(name2);

    }

    @Test
    @DisplayName("when products are searched by range of price")
    public void test4() {

        var name1 = "test1";
        var name2 = "test2";
        var name3 = "test3";
        var name4 = "test4";

        var price1 = new BigDecimal("120");
        var price2 = new BigDecimal("30");
        var price3 = new BigDecimal("250");
        var price4 = new BigDecimal("260");

        var rangeMin = new BigDecimal("100");
        var rangeMax = new BigDecimal("255");

        var product1 = ProductEntity.builder().name(name1).price(price1).build();
        var product2 = ProductEntity.builder().name(name2).price(price2).build();
        var product3 = ProductEntity.builder().name(name3).price(price3).build();
        var product4 = ProductEntity.builder().name(name4).price(price4).build();

        testEntityManager.persist(product1);
        testEntityManager.persist(product2);
        testEntityManager.persist(product3);
        testEntityManager.persist(product4);
        testEntityManager.flush();

        assertThat(productEntityDao.findAllByPriceBetween(rangeMin,rangeMax)
                .stream()
                .map(ProductEntity::getName)
                .toList())
                .hasSize(2)
                .containsExactly(name1,name3);

    }

    @Test
    @DisplayName("when products are searched by category")
    public void test5() {

        var name1 = "test1";
        var name2 = "test2";
        var name3 = "test3";
        var name4 = "test4";

        var category1 = Category.ELECTRONIC;
        var category2 = Category.BOOKS;

        var product1 = ProductEntity.builder().name(name1).category(category1).build();
        var product2 = ProductEntity.builder().name(name2).category(category2).build();
        var product3 = ProductEntity.builder().name(name3).category(category1).build();
        var product4 = ProductEntity.builder().name(name4).category(category2).build();

        testEntityManager.persist(product1);
        testEntityManager.persist(product2);
        testEntityManager.persist(product3);
        testEntityManager.persist(product4);
        testEntityManager.flush();

        assertThat(productEntityDao.findAllByCategory(category1)
                .stream()
                .map(ProductEntity::getName)
                .toList())
                .hasSize(2)
                .containsExactly(name1,name3);

    }

}
