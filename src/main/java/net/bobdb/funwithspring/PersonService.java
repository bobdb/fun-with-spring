package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person save(String name) {
        return personRepository.save(new Person(name));
    }

    public Person findById(Long id) throws Exception {
        return personRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    public List<Person> findAll() {
        Iterable<Person> list = personRepository.findAll();
        List<Person> persons = new ArrayList<>();
        list.forEach(persons::add);
        return persons;
    }
}
