package ru.skulkina.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.skulkina.dao.BookDao;
import ru.skulkina.models.Book;


import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;


    @Autowired
    public BookController( BookDao bookDao) {
        this.bookDao = bookDao;


    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("book",bookDao.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        //Получим 1 человека по ИД из Дао и передадим на отобржение в представление
        model.addAttribute("book",bookDao.show(id));
        return "book/show";
    }
    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book",new Book());
        return "book/new";
    }
    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult ) {

        if (bindingResult.hasErrors()) {return "book/new";}
        bookDao.save(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDao.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {return "book/edit";}
        bookDao.update(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/book";
    }
}
