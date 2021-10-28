package com.salescompany.orderservice.infrastructure.persistence;

import com.salescompany.orderservice.infrastructure.persistence.dao.OrderEntityDao;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import com.salescompany.orderservice.infrastructure.proxy.dto.GetProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;

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
    @DisplayName("when orders are searched by ids")
    public void test3() {

        var shopId = 6L;
        var shopId2 = 88L;
        var shopId3 = 77L;

        var order = OrderEntity.builder().shopId(shopId).build();
        var order2 = OrderEntity.builder().shopId(shopId2).build();
        var order3 = OrderEntity.builder().shopId(shopId3).build();

        testEntityManager.persist(order);
        testEntityManager.persist(order2);
        testEntityManager.persist(order3);
        testEntityManager.flush();

        assertThat(orderEntityDao.findAllById(List.of(5L, 7L)).stream().map(OrderEntity::getShopId).toList())
                .hasSize(2)
                .containsExactly(6L, 77L);
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
                .containsExactly(6L, 88L);
    }

    @Test
    @DisplayName("when order is deleted")
    public void test4() {

 /*       var order = OrderEntity.builder().shopId(77L).build();

 TODO

        testEntityManager.persist(order);
        testEntityManager.flush();

        var orderWithId = order.toOrder().withId(8L).toEntity();

        assertThat(orderEntityDao.delete(orderWithId).orElseThrow())*/

//        assertThat(orderEntityDao.delete(order))


    }

    @Test
    @DisplayName("when orders are searched by id of shop, should return list with 2 elements")
    public void test5() {

        var shopId = 6L;
        var shopId2 = 88L;
        var customerId = 3L;
        var customerId2 = 4L;
        var customerId3 = 5L;

        var order = OrderEntity.builder().customerId(customerId).shopId(shopId).build();
        var order2 = OrderEntity.builder().customerId(customerId2).shopId(shopId2).build();
        var order3 = OrderEntity.builder().customerId(customerId3).shopId(shopId).build();

        testEntityManager.persist(order);
        testEntityManager.persist(order2);
        testEntityManager.persist(order3);
        testEntityManager.flush();

        assertThat(orderEntityDao.findAllByShopId(shopId).stream().map(OrderEntity::getCustomerId).toList())
                .hasSize(2)
                .containsExactly(3L, 5L);
    }

    @Test
    @DisplayName("when orders are searched by id of shop, should return list with 2 elements")
    public void test6() {

        var shopId = 6L;
        var shopId2 = 88L;
        var shopId3 = 99L;
        var customerId = 3L;
        var customerId3 = 5L;

        var order = OrderEntity.builder().customerId(customerId).shopId(shopId).build();
        var order2 = OrderEntity.builder().customerId(customerId).shopId(shopId2).build();
        var order3 = OrderEntity.builder().customerId(customerId3).shopId(shopId3).build();

        testEntityManager.persist(order);
        testEntityManager.persist(order2);
        testEntityManager.persist(order3);
        testEntityManager.flush();

        assertThat(orderEntityDao.findAllByCustomerId(customerId).stream().map(OrderEntity::getShopId).toList())
                .hasSize(2)
                .containsExactly(6L, 88L);
    }

    @Test
    @DisplayName("when orders are searched by id of manager, should return list with 2 elements")
    public void test7() {

        var shopId = 6L;
        var shopId2 = 88L;
        var shopId3 = 99L;
        var managerId = 3L;
        var managerId2 = 5L;

        var order = OrderEntity.builder().managerId(managerId).shopId(shopId).build();
        var order2 = OrderEntity.builder().managerId(managerId2).shopId(shopId2).build();
        var order3 = OrderEntity.builder().managerId(managerId).shopId(shopId3).build();

        testEntityManager.persist(order);
        testEntityManager.persist(order2);
        testEntityManager.persist(order3);
        testEntityManager.flush();

        assertThat(orderEntityDao.findAllByManagerId(managerId).stream().map(OrderEntity::getShopId).toList())
                .hasSize(2)
                .containsExactly(6L, 99L);
    }


    @Test
    @DisplayName("when new order is created")
    public void test8() {

        var order = OrderEntity.builder().managerId(5L).shopId(7L).build();

        assertThat(orderEntityDao.save(order).getShopId())
                .isEqualTo(7L);

    }

    @Test
    @DisplayName("when order with multiple products is created")
    public void test9() {


        var map1 = new HashMap<Long,Integer>();
        map1.put(2L,67);
        map1.put(7L,25);


        var order1 = OrderEntity.builder().customerId(4L).productsMap(map1).build();


        assertThat(orderEntityDao.save(order1).getProductsMap())
                .hasSize(2)
                .containsAllEntriesOf(map1);

    }



}
