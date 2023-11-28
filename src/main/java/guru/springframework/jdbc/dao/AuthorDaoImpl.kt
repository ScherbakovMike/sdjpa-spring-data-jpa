package guru.springframework.jdbc.dao

import guru.springframework.jdbc.domain.Author
import guru.springframework.jdbc.repositories.AuthorRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by jt on 8/28/21.
 */
@Component
open class AuthorDaoImpl(private val authorRepository: AuthorRepository) : AuthorDao {

    override fun getById(id: Long): Author? {
        return authorRepository.getReferenceById(id)
    }

    override fun findAuthorByName(firstName: String, lastName: String): Optional<Author>? {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
    }

    override fun saveNewAuthor(author: Author): Author? {
        return authorRepository.save(author)
    }

    @Transactional
    override fun updateAuthor(author: Author): Author? {
        val foundAuthor = authorRepository.getReferenceById(author.id)
        foundAuthor.firstName = author.firstName
        foundAuthor.lastName = author.lastName
        return authorRepository.save(foundAuthor)
    }

    override fun deleteAuthorById(id: Long) {
        authorRepository.deleteById(id)
    }
}
