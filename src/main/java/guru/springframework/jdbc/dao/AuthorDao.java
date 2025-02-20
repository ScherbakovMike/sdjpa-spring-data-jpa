package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import java.util.Optional;

/**
 * Created by jt on 8/22/21.
 */
public interface AuthorDao {
    Author getById(Long id);

    Optional<Author> findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
