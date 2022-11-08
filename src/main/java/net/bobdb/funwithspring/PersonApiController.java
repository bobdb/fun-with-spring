package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonApiController {

    @Autowired
    PersonService personService;

    @GetMapping("/api/person")
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok().body(personService.findAll().get());
    }

    @GetMapping(path = "/api/person/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Optional<Person> optionalPerson = personService.findById(id);

        if (optionalPerson.isPresent())
            return ResponseEntity.ok().body(optionalPerson.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(path = "/api/search/{name}")
    public ResponseEntity<Person> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(personService.findByName(name).get());
    }

    @PostMapping(path = "/api/person/{name}")
    public Person create(@PathVariable String name) {
        return personService.save(name);
    }

    @PutMapping(path = "/api/person/{id}")
    public ResponseEntity<Person> update(@Valid @RequestBody Person person) throws Exception {
        Person updatedPerson = personService.update(person);
        return ResponseEntity.ok().body(updatedPerson);
    }

    @DeleteMapping(path = "/api/person/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Long deletedId = personService.delete(id);
        return ResponseEntity.ok().body(deletedId);
    }

}