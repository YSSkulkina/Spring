package ru.skulkina.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skulkina.models.Book;
import ru.skulkina.models.Person;
import ru.skulkina.repositories.BookRepository;
import ru.skulkina.repositories.PersonRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findById(int id){
        Optional<Person> foundPerson=personRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save(Person person){
        personRepository.save(person);

}
    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }
    @Transactional
    public void update(int id,Person updatedPerson){
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book ->
            {long time=Math.abs(book.getTakenAt().getTime()-new Date().getTime());
                if (time>864000000) book.setExpired(true);

            });

//
            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }





}
