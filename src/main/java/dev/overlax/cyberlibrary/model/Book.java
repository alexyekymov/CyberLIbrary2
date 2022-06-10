package dev.overlax.cyberlibrary.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Description shouldn't be empty!")
    @Size(min=1, max = 100, message = "Description should be between 2 and 100 characters.")
    private String description;

    @Size(max = 100, message = "Author's name should be max 100 characters.")
    private String author;

    @Min(value = 0, message = "The created date should be in AD.")
    private int created;

    private Integer personId;

    public Book() {
    }

    public Book(String description, String author, int created, int personId) {
        this.description = description;
        this.author = author;
        this.created = created;
        this.personId = personId;
    }

    public Book(int id, String description, String author) {
        this.id = id;
        this.description = description;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
