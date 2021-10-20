package com.salescompany.productservice.infrastructure.persistence;

import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.infrastructure.persistence.dao.ProducerEntityDao;
import com.salescompany.productservice.infrastructure.persistence.entity.ProducerEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProducerEntityDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProducerEntityDao producerEntityDao;

    @Test
    @DisplayName("when all users are searched")
    public void test1() {

        var producer1 = ProducerEntity.builder().name("tech corp").industry(Industry.ELECTRONIC).build();
        var producer2 = ProducerEntity.builder().name("food corp").industry(Industry.ELECTRONIC).build();

        testEntityManager.persist(producer1);
        testEntityManager.persist(producer2);
        testEntityManager.flush();

        var expectedNames = List.of("tech corp", "food corp");

        Assertions.assertThat(producerEntityDao.findAll().stream().map(ProducerEntity::getName).toList())
                .hasSize(2)
                .containsAll(expectedNames);
    }

    @Test
    @DisplayName("when producers are searched by name")
    public void test2() {

        var producer1 = ProducerEntity.builder().name("tech corporation").industry(Industry.ELECTRONIC).build();
        var producer2 = ProducerEntity.builder().name("food corporation").industry(Industry.ELECTRONIC).build();

        testEntityManager.persist(producer1);
        testEntityManager.persist(producer2);
        testEntityManager.flush();


        Assertions.assertThat(producerEntityDao.findByName("tech cosporation"))
                .isPresent();
    }

    @Test
    @DisplayName("when producers are searched by industry")
    public void test3() {

        var producer1 = ProducerEntity.builder().name("tech corporation").industry(Industry.ELECTRONIC).build();
        var producer2 = ProducerEntity.builder().name("furniture corporation").industry(Industry.FURNITURE).build();
        var producer3 = ProducerEntity.builder().name("food corporation").industry(Industry.ELECTRONIC).build();

        testEntityManager.persist(producer1);
        testEntityManager.persist(producer2);
        testEntityManager.persist(producer3);
        testEntityManager.flush();

        var expectedNames = List.of("tech corporation","food corporation");

        Assertions.assertThat(producerEntityDao.findAllByIndustry(Industry.ELECTRONIC)
                        .stream()
                        .map(ProducerEntity::getName)
                        .toList())
                .hasSize(2)
                .containsAll(expectedNames);
    }

    @Test
    @DisplayName("when new user is created")
    public void test4() {

        var producer1 = ProducerEntity.builder().name("tech corporation").industry(Industry.ELECTRONIC).build();

        Assertions.assertThat(producerEntityDao.save(producer1).getName())
                .isEqualTo("tech corporation");
    }

}
