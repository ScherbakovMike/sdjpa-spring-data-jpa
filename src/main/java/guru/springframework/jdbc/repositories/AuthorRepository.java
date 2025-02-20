package guru.springframework.jdbc.repositories;


import guru.springframework.jdbc.domain.Author;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
