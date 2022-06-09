package dev.overlax.cyberlibrary.controllers;

import dev.overlax.cyberlibrary.dao.PersonDAO;
import dev.overlax.cyberlibrary.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Person> personOpt = personDAO.show(id);
        personOpt.ifPresent(model::addAttribute);

        model.addAttribute(personDAO.bookIndex(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute(new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Optional<Person> personOpt = personDAO.show(id);
        personOpt.ifPresent(person -> model.addAttribute("person", person));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/people/edit";
        personDAO.edit(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
