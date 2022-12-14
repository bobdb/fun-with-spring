package net.bobdb.funwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public String findAll(Model model) {
        List<Person> persons = personService.findAll().orElse(List.of());
        Person find = new Person();
        model.addAttribute("persons", persons);
        model.addAttribute("find", find);
        return "person";
    }

    @GetMapping(path = "/person/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Person person = personService.findById(id).orElse(new Person());
        model.addAttribute("persons", List.of(person));
        return "person";
    }

    @PostMapping(path = "/person")
    public String create(@ModelAttribute Person find, Model model) {
        personService.save(find.getName());
        List<Person> persons = personService.findAll().orElse(List.of());
        model.addAttribute("persons", persons);
        model.addAttribute("find", find);
        return "person";
    }

    @PostMapping(path = "/person/delete")
    public String delete(@ModelAttribute Person find, Model model) {
        Optional<Person> p = personService.findByName(find.getName());
        if (p.isPresent()) {
            personService.delete(p.get().getId());
        }
        List<Person> persons = personService.findAll().orElse(List.of());
        model.addAttribute("persons", persons);
        model.addAttribute("find", find);
        return "person";
    }

    @PostMapping(path = "/person/deleteById")
    public String deleteById(@ModelAttribute Person find, Model model) {
        Optional<Person> p = personService.findById(find.getId());
        if (p.isPresent()) {
            personService.delete(p.get().getId());
        }

        List<Person> persons = personService.findAll().orElse(List.of());
        model.addAttribute("persons", persons);
        model.addAttribute("find", find);
        return "person";
    }

}