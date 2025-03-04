package ru.skulkina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.skulkina.models.Person;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

}

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE PERSON SET name=?, age=?,email=? WHERE id=?",updatePerson.getName(),updatePerson.getAge(),updatePerson.getEmail(),id);

    }
    public void delete(int id){
       jdbcTemplate.update("DELETE FROM PERSON WHERE id=?",id);
    }
}
