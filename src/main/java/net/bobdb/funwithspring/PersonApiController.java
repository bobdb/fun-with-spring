package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonApiController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/api/person")
    public List<Person> person(String name) {
        Iterable<Person> persons = personRepository.findAll();

        List<Person> l = new ArrayList<Person>();
        persons.forEach(l::add);

        return l;
    }

    @GetMapping(path = "/api/person/{id}") // TODO maybe optional
    public Person findById(@PathVariable Long id) throws Exception {

        return personRepository.findById(id)
                .orElseThrow(() -> new Exception());

    }

}