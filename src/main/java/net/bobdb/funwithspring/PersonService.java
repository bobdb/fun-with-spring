package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person save(String name) {
        return personRepository.save(new Person(name));
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }
    public Optional<Person> findByName(String name) {
        return Optional.ofNullable(personRepository.findByName(name));
    }

    public Optional<List<Person>> findAll() {
        Iterable<Person> list = personRepository.findAll();
        List<Person> persons = new ArrayList<>();
        list.forEach(persons::add);
        return Optional.of(persons);
    }

    public Long delete(Long id) {
        personRepository.deleteById(id);
        return id;
    }

    public Person update(Person person) throws Exception {
        Person p = personRepository.findById(person.getId()).orElseThrow(Exception::new);

        if (Objects.nonNull(person.getName())) {
            p.setName(person.getName());
        }

        return personRepository.save(p);
    }

}
