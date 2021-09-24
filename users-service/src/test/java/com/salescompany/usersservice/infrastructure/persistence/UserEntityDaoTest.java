package com.salescompany.usersservice.infrastructure.persistence;

import com.salescompany.usersservice.infrastructure.persistence.dao.UserEntityDao;
import com.salescompany.usersservice.infrastructure.persistence.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserEntityDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserEntityDao userEntityDao;


    @Test
    @DisplayName("when all users are found correctly")
    public void test1() {

        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();


        var insertedUser1 = UserEntity.builder().id(1L).username(username1).mail(mail1).build();
        var insertedUser2 = UserEntity.builder().id(2L).username(username2).mail(mail2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(userEntityDao.findAll())
                .hasSize(2)
                .containsExactly(insertedUser1,insertedUser2);

    }

    @Test
    @DisplayName("when user is searched by id")
    public void test2() {

        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";

        var insertedUser2 = UserEntity.builder().id(4L).username(username2).mail(mail2).build();

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();


        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();


        assertThat(userEntityDao.findById(4L))
                .isEqualTo(Optional.of(insertedUser2));

    }

    @Test
    @DisplayName("when user is searched by mail")
    public void test3() {

        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";

        var insertedUser1 = UserEntity.builder().id(5L).username(username1).mail(mail1).build();
        var insertedUser2 = UserEntity.builder().id(2L).username(username2).mail(mail2).build();

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(userEntityDao.findByMail(mail1))
                .isEqualTo(Optional.of(insertedUser1));

    }

    @Test
    @DisplayName("when user is searched by username")
    public void test4() {

        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";

        var insertedUser2 = UserEntity.builder().id(8L).username(username2).mail(mail2).build();

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(userEntityDao.findByUsername(username2))
                .isEqualTo(Optional.of(insertedUser2));
    }

    @Test
    @DisplayName("when user is updated")
    public void test5() {
// TODO
        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";

        var id = 9L;

        var insertedUser2 = UserEntity.builder().id(9L).username(username2).mail(mail2).build();

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();

        testEntityManager.persist(user1);

        testEntityManager.flush();

        assertThat(userEntityDao.findByUsername(username2))
                .isEqualTo(Optional.of(insertedUser2));
    }



}
