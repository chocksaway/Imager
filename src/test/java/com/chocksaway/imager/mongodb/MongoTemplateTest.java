package com.chocksaway.imager.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import com.chocksaway.imager.config.MongoConfig;
import com.chocksaway.imager.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MongoConfig.class)
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }
    }

    @AfterEach
    public void tearDown() {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    public void givenUserExists_whenSaving_thenUserIsCreated() {
        var user = User.builder()
                .username("John")
                .age(28)
                .build();

        mongoTemplate.insert(user);

        assertThat(user.getUsername(), is(notNullValue()));
    }

    @Test
    public void simpleTest() {
        assertThat(1, is(1));
    }

    @Test
    public void givenUsersExist_whenFindingUserWithAgeLessThan50AndGreaterThan20_thenUsersAreFound() {
        var user = User.builder()
                .username("Eric")
                .age(35)
                .build();

        mongoTemplate.insert(user);
        user = User.builder().username("Richard").build();
        mongoTemplate.insert(user);

        Query query = new Query();
        query.addCriteria(Criteria.where("age").lt(50).gt(20));
        List<User> users = mongoTemplate.find(query, User.class);

        assertThat(users.size(), is(1));
    }
}