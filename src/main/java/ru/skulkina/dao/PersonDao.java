package ru.skulkina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.skulkina.models.Book;
import ru.skulkina.models.Person;
import ru.skulkina.util.PersonValidator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;



    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));

    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show (String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO PERSON (name,year_of_birth) VALUES( ?, ? )", person.getName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE PERSON SET name=?, year_of_birth=? WHERE id=?",updatePerson.getName(),updatePerson.getYearOfBirth(),id);

    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM PERSON WHERE id=?",id);
    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM book where person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

}
