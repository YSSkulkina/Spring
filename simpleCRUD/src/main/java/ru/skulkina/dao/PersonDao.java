package ru.skulkina.dao;

import org.springframework.stereotype.Component;
import ru.skulkina.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDao {
    private static int PERSON_ID;
    private List<Person> people;

    {
        people = new ArrayList<Person>();
        people.add(new Person("Tom",++PERSON_ID, 12,"tom@gmail.com"));
        people.add(new Person("Jack",++PERSON_ID,45,"jack@gmail.com"));
        people.add(new Person("Bob",++PERSON_ID,27,"bob@gmail.com"));
        people.add(new Person("Jane",++PERSON_ID,67,"jane@gmail.com"));


    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
//        for(Person p : people){
//            if(p.getId() == id){
//                return p;}
//             }
//        return null;
        return people.stream().filter(p -> p.getId() == id).findAny().orElse(null);

    }

    public void save(Person person){
        person.setId(++PERSON_ID);
        people.add(person);
    }

    public void update(int id, Person updatePerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());

    }
    public void delete(int id){
        people.removeIf(p->p.getId() == id);
    }
}
