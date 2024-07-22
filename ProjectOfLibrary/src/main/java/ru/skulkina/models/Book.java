package ru.skulkina.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min=2, max=100, message = "Title should be between 2 and 100")
    private String title;
    @NotEmpty(message = "Title should not be empty")
    @Size(min=2, max=100, message = "Title should be between 2 and 100")
    private String author;
    @Min(value=1800,message = "Year of production should be greater than 1900")
    private int year;



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


}
