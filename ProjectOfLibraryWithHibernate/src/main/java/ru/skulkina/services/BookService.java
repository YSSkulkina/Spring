package ru.skulkina.services;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skulkina.models.Book;
import ru.skulkina.models.Person;
import ru.skulkina.repositories.BookRepository;

import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findAll(boolean sort_by_year) {
        return bookRepository.findAll(Sort.by("year"));
    }
    public List<Book> findAll(Integer page,Integer books_per_page,boolean sort_by_year) {
        return bookRepository.findAll(PageRequest.of(page,books_per_page,Sort.by("year"))).getContent();
    }
    public Book findById(int id) {
        Optional<Book> foundBook=bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);

    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
    @Transactional
    public void update(int id,Book updatedBook){
        updatedBook.setId(id);
        updatedBook.setOwner(updatedBook.getOwner());
        bookRepository.save(updatedBook);
    }

    public Person getBookOwner(int id){
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);

    }
    @Transactional
    //Освобождает книгу
    public void passBook(int id){
        bookRepository.findById(id).ifPresent(book ->
        {book.setOwner(null);
        book.setTakenAt(null);});

    }
    @Transactional
    //Назначает книгу
    public void getBook(int id,Person selectedPerson){
        bookRepository.findById(id).ifPresent(book ->
        {book.setOwner(selectedPerson);
        book.setTakenAt(new Date());});

    }

    //Поиск книги по названию
    public List <Book> findBookByTitle(String title){
        return bookRepository.findByTitleStartingWith(title);
    }
}
