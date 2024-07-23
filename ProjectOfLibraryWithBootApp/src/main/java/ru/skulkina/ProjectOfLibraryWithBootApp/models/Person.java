package ru.skulkina.ProjectOfLibraryWithBootApp.models;



import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=100, message = "Name should be between 2 and 100")
    private String name;

    @Column(name="year_of_birth")
    @Min(value=1900,message = "Year of bith should be greater than 1900")
    private int yearOfBirth;

    @OneToMany (mappedBy = "owner",fetch = FetchType.EAGER)
    private List<Book> books;

    public Person(){}

    public Person( String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(@Min(value = 1900, message = "Year of birth should be greater than 1900") int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
