package ru.skulkina.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.skulkina.models.Person;
import ru.skulkina.services.PersonService;


import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

  private final PersonService personService;


    @Autowired
    public PeopleController( PersonService personService) {
        this.personService = personService;

    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people",personService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        //Получим 1 человека по ИД из Дао и передадим на отобржение в представление
        model.addAttribute("person",personService.findById(id));
        model.addAttribute("books",personService.getBooksByPersonId(id));

        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person",new Person());
        return "people/new";
    }
    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult ) {

        if (bindingResult.hasErrors()) {return "people/new";}
        personService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editePerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personService.findById(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {return "people/edit";}
        personService.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }


}
