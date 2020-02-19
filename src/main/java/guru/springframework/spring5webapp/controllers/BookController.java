package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepo bookRep;

    public BookController(BookRepo bookRep) {
        this.bookRep = bookRep;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRep.findAll());
        return "books/list";
    }
}
