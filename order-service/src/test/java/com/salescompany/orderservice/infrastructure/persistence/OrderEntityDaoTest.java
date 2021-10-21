package com.salescompany.orderservice.infrastructure.persistence;

import com.salescompany.orderservice.infrastructure.persistence.dao.OrderEntityDao;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderEntityDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderEntityDao orderEntityDao;


    @Test
    @DisplayName("when order is searched by id")
    public void test1() {

        var shopId = 6L;
        var shopId2 = 88L;

        var order = OrderEntity.builder().shopId(shopId).build();
        var order2 = OrderEntity.builder().shopId(shopId2).build();

        testEntityManager.persist(order);
        testEntityManager.persist(order2);
        testEntityManager.flush();

        assertThat(orderEntityDao.findById(1L).orElseThrow().getShopId())
                .isEqualTo(6L);
    }

    @Test
    @DisplayName("when all orders are searched")
    public void test2() {

        var shopId = 6L;
        var shopId2 = 88L;

        var order = OrderEntity.builder().shopId(shopId).build();
        var order2 = OrderEntity.builder().shopId(shopId2).build();

        testEntityManager.persist(order);
        testEntityManager.persist(order2);
        testEntityManager.flush();

        assertThat(orderEntityDao.findAll().stream().map(OrderEntity::getShopId).toList())
                .hasSize(2)
                .containsExactly(6L,88L);
    }
}
