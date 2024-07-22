package ru.skulkina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import ru.skulkina.models.Book;
import ru.skulkina.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

   List<Book>  findByTitleStartingWith(String title);
}
