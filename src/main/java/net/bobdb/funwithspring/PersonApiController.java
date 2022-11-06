package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonApiController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @GetMapping("/api/person")
    public List<Person> findAll() {
        Iterable<Person> persons = personRepository.findAll();
        List<Person> l = new ArrayList<>();
        persons.forEach(l::add);
        return l;
    }

    @GetMapping(path = "/api/person/{id}")
    public Person findById(@PathVariable Long id) throws Exception {
        return personRepository.findById(id)
                .orElseThrow(Exception::new);

    }

    @PutMapping(path = "/api/person/")
    public Person create(@PathVariable String name) {
        return personService.save(name);

    }

}