package ru.skulkina.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.skulkina.dao.PersonDao;
import ru.skulkina.models.Person;

    @Component
    public class PersonValidator implements Validator {

        private final PersonDao personDao;
        @Autowired
        public PersonValidator(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return Person.class.equals(aClass);

        }

        @Override
        public void validate(Object o, Errors errors) {
            Person person = (Person) o;
            if(personDao.show(person.getName()).isPresent())
            {errors.rejectValue("name", null, "Человек с таким имененм уже заведен");}
        }
    }


