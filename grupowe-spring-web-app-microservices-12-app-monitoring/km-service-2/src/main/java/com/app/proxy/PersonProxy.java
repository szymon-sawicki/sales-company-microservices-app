package com.app.proxy;

import com.app.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "km-service-1")
public interface PersonProxy {
    @GetMapping("/people")
    List<Person> getAll();

    @GetMapping("/people/{id}")
    Person getOne(@PathVariable("id") Integer id);
}
