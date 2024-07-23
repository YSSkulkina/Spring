package ru.skulkina.ProjectOfLibraryWithBootApp.models;



import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min=2, max=100, message = "Title should be between 2 and 100")
    private String title;

    @Column(name="author")
    @NotEmpty(message = "Title should not be empty")
    @Size(min=2, max=100, message = "Title should be between 2 and 100")
    private String author;

    @Column(name="year")
    @Min(value=1800,message = "Year of production should be greater than 1900")
    private int year;

    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "id")
    private Person owner;

    @Column (name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    public Book() {}

    public Book(String title, String author, int year) {

        this.title = title;
        this.author = author;
        this.year = year;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Title should not be empty") @Size(min = 2, max = 100, message = "Title should be between 2 and 100") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Title should not be empty") @Size(min = 2, max = 100, message = "Title should be between 2 and 100") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Title should not be empty") @Size(min = 2, max = 100, message = "Title should be between 2 and 100") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty(message = "Title should not be empty") @Size(min = 2, max = 100, message = "Title should be between 2 and 100") String author) {
        this.author = author;
    }

    @Min(value = 1800, message = "Year of production should be greater than 1900")
    public int getYear() {
        return year;
    }

    public void setYear(@Min(value = 1800, message = "Year of production should be greater than 1900") int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
