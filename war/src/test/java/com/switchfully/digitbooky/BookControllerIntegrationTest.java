package com.switchfully.digitbooky;

import com.switchfully.digitbooky.domain.Author;
import com.switchfully.digitbooky.domain.Book;
import com.switchfully.digitbooky.domain.BookRepository;
import com.switchfully.digitbooky.service.BookMapper;
import com.switchfully.digitbooky.service.dto.BookDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private final BookMapper bookMapper = new BookMapper();

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void createAndFillBookRepository() {
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
                .baseUri("http://localhost")
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
}
