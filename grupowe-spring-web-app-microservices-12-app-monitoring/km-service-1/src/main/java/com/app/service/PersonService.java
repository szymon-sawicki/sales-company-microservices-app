package com.app.service;

import com.app.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private Logger logger = LoggerFactory.getLogger(PersonService.class);

    public List<Person> getAll() {
        logger.info("GET ALL");
        return List.of(
                Person.builder().id(1).name("A").age(10).build(),
                Person.builder().id(2).name("B").age(20).build()
        );
    }

    public Person getOne(Integer id) {
        logger.info("GET ONE");
        return Person.builder().id(id).name("C").age(20).build();
    }
}
