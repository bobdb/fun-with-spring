package net.bobdb.funwithspring;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person")
    public String person(String name, Model model) {
        Iterable<Person> persons = personRepository.findAll();

        List<Person> l = new ArrayList<Person>();
        persons.forEach(l::add);

        model.addAttribute("persons", l);
        return "person";
    }

    @GetMapping(path = "/person/{id}")
    public String findById(@PathVariable Long id, Model model) throws Exception {

        Person person = personRepository.findById(id)
                .orElseThrow(()->new Exception());

        List<Person> l = List.of(person);

        model.addAttribute("persons", l);
        return "person";

    }

}