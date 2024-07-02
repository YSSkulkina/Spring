package ru.skulkina.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.skulkina.dao.PersonDao;
import ru.skulkina.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передедим на отображение в представление
        model.addAttribute("people",personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
       //Получим 1 человека по ИД из Дао и передадим на отобржение в представление
       model.addAttribute("person",personDao.show(id));
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
        personDao.save(person);
        return "redirect:/people";
}
@GetMapping("/{id}/edit")
public String editePerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDao.show(id));
return "people/edit";
}
@PatchMapping("/{id}")
public String updatePerson(@ModelAttribute("person") @Valid Person person,
                           BindingResult bindingResult,
                           @PathVariable("id") int id) {

       if (bindingResult.hasErrors()) {return "people/edit";}
           personDao.update(id, person);
return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
       personDao.delete(id);
        return "redirect:/people";
    }
}
