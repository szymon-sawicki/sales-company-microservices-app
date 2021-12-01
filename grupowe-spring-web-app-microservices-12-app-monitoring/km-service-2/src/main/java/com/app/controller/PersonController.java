package com.app.controller;

import com.app.model.Person;
import com.app.proxy.PersonProxy;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PersonProxy personProxy;

    @GetMapping("/people")
    @Timed(value = "greeting.time", description = "Time to get all people")
    public List<Person> getAll() {
        logger.info("GET ALL");
        return personProxy.getAll();
    }

    @GetMapping("/people/{id}")
    Person getOne(@PathVariable("id") Integer id) {
        logger.info("GET ONE");
        return personProxy.getOne(id);
    }
}
