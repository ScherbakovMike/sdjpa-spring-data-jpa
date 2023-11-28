package guru.springframework.jdbc

import guru.springframework.jdbc.repositories.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
open class BookRepositoryTest {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun testEmptyResultException() {
        assertThrows<EmptyResultDataAccessException> {
            val book = bookRepository.readByTitle("foobar4")
        }
    }

    @Test
    fun testNullParam() {
        assertNull(bookRepository.getByTitle(null))
    }

    @Test
    fun testNoException() {
        assertNull(bookRepository.getByTitle("foo"))
    }

    @Test
    fun testBookStream() {
        val count = AtomicInteger()
        bookRepository.findAllByTitleNotNull().forEach {
            count.incrementAndGet()
        }
        assertThat(count.get()).isGreaterThanOrEqualTo(5)
    }

    @Test
    fun testBookFuture() {
        val bookFuture = bookRepository.queryByTitle("Clean Code")
        assertNotNull(bookFuture.get())
    }

    @Test
    fun testBookQuery() {
        assertThat(bookRepository.findBookByTitleWithQuery("Clean Code")).isNotNull()
    }

    @Test
    fun testBookQueryNamed() {
        assertThat(bookRepository.findBookByTitleWithQueryNamed("Clean Code")).isNotNull()
    }

    @Test
    fun testBookNativeQueryNamed() {
        assertThat(bookRepository.findBookByTitleNative("Clean Code")).isNotNull()
    }

    @Test
    fun testBookNamed() {
        assertThat(bookRepository.jpaNamed("Clean Code")).isNotNull()
    }
}