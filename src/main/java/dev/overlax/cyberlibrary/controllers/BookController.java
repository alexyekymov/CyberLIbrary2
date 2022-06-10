package dev.overlax.cyberlibrary.controllers;

import dev.overlax.cyberlibrary.dao.BookDAO;
import dev.overlax.cyberlibrary.dao.PersonDAO;
import dev.overlax.cyberlibrary.model.Book;
import dev.overlax.cyberlibrary.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model, @ModelAttribute("person") Person person) {
        Optional<Book> bookOpt = bookDAO.show(id);
        bookOpt.ifPresent(book -> model.addAttribute("book", book));

        bookOpt.ifPresent(book -> {
            if (book.getPersonId() == null) {
                model.addAttribute("people", personDAO.index());
            } else {
                Optional<Person> personOpt = personDAO.show(book.getPersonId());
                personOpt.ifPresent(p -> model.addAttribute("person", p));
            }
        });

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        Optional<Book> bookOpt = bookDAO.show(id);
        bookOpt.ifPresent(book -> model.addAttribute("book", book));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable int id,
                         @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person.getId());
        return "redirect:/books/{id}";
    }
}
