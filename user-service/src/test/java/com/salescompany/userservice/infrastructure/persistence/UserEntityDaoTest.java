package com.salescompany.userservice.infrastructure.persistence;

import com.salescompany.userservice.infrastructure.persistence.dao.UserEntityDao;
import com.salescompany.userservice.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestBody;

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

        var insertedUser1 = UserEntity.builder().username(username1).mail(mail1).build();
        var insertedUser2 = UserEntity.builder().username(username2).mail(mail2).build();

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(userEntityDao.findByMail(mail1).orElseThrow().getUsername())
                .isEqualTo(username1);

    }

    @Test
    @DisplayName("when user is searched by username")
    public void test4() {

        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";

        var user1 = UserEntity.builder().username(username1).mail(mail1).build();
        var user2 = UserEntity.builder().username(username2).mail(mail2).build();

        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        assertThat(userEntityDao.findByUsername(username1).orElseThrow().getUsername())
                .isEqualTo(username1);
    }

    @Test
    @DisplayName("when user is updated")
    public void test5() {
// TODO
        var username1 ="aaabbb";
        var mail1 = "bbbbbb@aaaa.com";
        var username2 = "cccccccccc";
        var mail2 = "ccccccc@ccccc.com";


        var user1 = UserEntity.builder().username(username1).mail(mail1).build();

        testEntityManager.persist(user1);

        testEntityManager.flush();


        var id = userEntityDao.findByUsername(username1).orElseThrow().getId();
        var userUpdate = UserEntity.builder().id(id).username(username2).mail(mail2).build();



        assertThat(userEntityDao.save(userUpdate).getUsername())
                .isEqualTo(username2);
    }



}
