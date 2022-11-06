package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public String findAll(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);
        return "person";
    }

    @GetMapping(path = "/person/{id}")
    public String findById(@PathVariable Long id, Model model) throws Exception {
        Person person = personService.findById(id);
        model.addAttribute("persons", List.of(person));
        return "person";
    }

}