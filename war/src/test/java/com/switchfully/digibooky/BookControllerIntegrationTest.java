package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.books.Author;
import com.switchfully.digibooky.domain.books.Book;
import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.service.books.BookMapper;
import com.switchfully.digibooky.service.books.dto.BookDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private final BookMapper bookMapper = new BookMapper();

    private static final String URI = "http://localhost";

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void createAndFillBookRepository() {
        bookRepository.getAllBooks().clear();
        bookRepository.saveBook(new Book("isbn1", "title1", new Author("first1", "last1")));
        bookRepository.saveBook(new Book("isbn2", "title2", new Author("first2", "last2")));
        bookRepository.saveBook(new Book("isbn3", "title3", new Author("first3", "last3")));
        bookRepository.saveBook(new Book("isbn4", "title4", new Author("first4", "last4")));
    }

    @Test
    void getAllBooks() {
        List<BookDTO> expected = bookRepository.getAllBooks().stream()
                .map(bookMapper::mapBookToDTO)
                .toList();

        BookDTO[] result = RestAssured
                .given()
                .baseUri(URI)
                .port(port)
                .when()
                .get("/books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(BookDTO[].class);

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result).containsAll(expected);
    }

    @Test
    void getBookByISBN_GivenValidISBN() {
        String isbn = "isbn1";
        BookDTO expected = bookMapper.mapBookToDTO(bookRepository.getBookByISBN(isbn));

        BookDTO result = RestAssured
                .given()
                .baseUri(URI)
                .port(port)
                .when()
                .get("/books/".concat(isbn))
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(BookDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getIsbn()).isEqualTo(expected.getIsbn());
        assertThat(result.getTitle()).isEqualTo(expected.getTitle());
        assertThat(result.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(result.getSummary()).isEqualTo(expected.getSummary());
        assertThat(result).isEqualTo(expected);
    }
}
