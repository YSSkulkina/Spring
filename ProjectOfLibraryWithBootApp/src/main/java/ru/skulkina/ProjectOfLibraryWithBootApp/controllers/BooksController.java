package ru.skulkina.ProjectOfLibraryWithBootApp.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.skulkina.ProjectOfLibraryWithBootApp.models.Book;
import ru.skulkina.ProjectOfLibraryWithBootApp.services.BookService;
import ru.skulkina.ProjectOfLibraryWithBootApp.services.PersonService;
import ru.skulkina.ProjectOfLibraryWithBootApp.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BooksController {

    private final BookService bookService;
    private final PersonService personService;


    @Autowired
    public BooksController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam (value = "page", required = false) Integer page ,
                        @RequestParam (value = "books_per_page", required = false) Integer books_per_page,
                        @RequestParam (value = "sort_by_year",required = false) boolean sort_by_year
                        ) {
    if (sort_by_year) { model.addAttribute("book", bookService.findAll(sort_by_year)); }
        //Получим всех людей из DAO и передадим на отображение в представление
    else if (page != null && books_per_page!=null) {model.addAttribute("book",bookService.findAll(page,books_per_page,sort_by_year));}
    else  {model.addAttribute("book",bookService.findAll());}

        return "book/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person) {
        //Получим 1 человека по ИД из Дао и передадим на отобржение в представление
        model.addAttribute("book",bookService.findById(id));

        Person bookOwner = bookService.getBookOwner(id);

        if (bookOwner!=null) {
            model.addAttribute("owner", bookOwner); }
        else model.addAttribute("people",personService.findAll());

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
        bookService.save(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookService.findById(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {return "book/edit";}
        bookService.update(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/pass")
    public String passBook(@PathVariable("id") int id){
        bookService.passBook(id);
        return "redirect:/book/"+id;
    }
    @PatchMapping("/{id}/get")
    public String getBook(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        bookService.getBook(id,selectedPerson);
        return "redirect:/book/"+id;
    }

    @GetMapping("/search")
    public String bookSearch(Model model) {

        return "book/search";
    }
    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("title") String title) {
        model.addAttribute("books", bookService.findBookByTitle(title));
        return "book/search";
    }

}
