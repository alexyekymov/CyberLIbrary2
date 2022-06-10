package dev.overlax.cyberlibrary.dao;

import dev.overlax.cyberlibrary.model.Book;
import dev.overlax.cyberlibrary.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT id, description, author, created, person_id FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class)).stream()
                .findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(description, author, created) VALUES (?, ?, ?)",
                book.getDescription(), book.getAuthor(), book.getCreated());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET description=?, author=?, created=?, person_id=? WHERE id=?",
                book.getDescription(), book.getAuthor(), book.getCreated(), book.getPersonId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assign(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, id);
    }
}
