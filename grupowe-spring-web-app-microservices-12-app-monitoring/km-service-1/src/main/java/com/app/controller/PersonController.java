package com.app.controller;

import com.app.model.Person;
import com.app.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/people")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/people/{id}")
    public Person getOne(@PathVariable Integer id) {
        return personService.getOne(id);
    }
}
