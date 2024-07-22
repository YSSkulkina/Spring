package ru.skulkina.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=100, message = "Name should be between 2 and 100")
    private String name;
    @Min(value=1900,message = "Year of bith should be greater than 1900")
    private int yearOfBirth;

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


}
