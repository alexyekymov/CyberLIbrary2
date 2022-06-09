package dev.overlax.cyberlibrary.model;

public class Book {
    private int id;
    private String description;
    private String author;
    private int created;
    private Integer personId;

    public Book() {
    }

    public Book(int id, String description, String author, int created, int personId) {
        this.id = id;
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
