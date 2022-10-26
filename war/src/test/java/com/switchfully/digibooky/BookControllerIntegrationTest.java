package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.bookLoans.BookLoanOut;
import com.switchfully.digibooky.domain.bookLoans.BookLoanRepository;
import com.switchfully.digibooky.domain.books.Author;
import com.switchfully.digibooky.domain.books.Book;
import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.domain.users.MemberRepository;
import com.switchfully.digibooky.service.bookLoan.BookLoanMapper;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanInDTO;
import com.switchfully.digibooky.service.bookLoan.dto.BookLoanOutDTO;
import com.switchfully.digibooky.service.books.BookMapper;
import com.switchfully.digibooky.service.books.dto.BookDTO;
import io.restassured.RestAssured;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = BookControllerIntegrationTest.class)
public class BookControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private final BookMapper bookMapper = new BookMapper();

    private static final String URI = "http://localhost";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    BookLoanRepository bookLoanRepository;

    @BeforeEach
    void createAndFillBookRepository() {
        bookRepository.getAllBooks().clear();
        bookRepository.saveBook(new Book("isbn1", "title1", new Author("first1", "last1")));
        bookRepository.saveBook(new Book("isbn2", "title2", new Author("first2", "last2")));
        bookRepository.saveBook(new Book("isbn3", "title3", new Author("first3", "last3")));
        bookRepository.saveBook(new Book("isbn4", "title4", new Author("first4", "last4")));
    }

    @BeforeEach
    void createAndFillMemberRepository() {
        memberRepository.getAll().clear();
        memberRepository.saveMember(new Member("inss1", "first1", "password", "test@test.be", new Address("city1")));
        memberRepository.saveMember(new Member("inss2", "first2", "password", "tes2@test.be", new Address("city2")));
        memberRepository.saveMember(new Member("inss3", "first3", "password", "tes3@test.be", new Address("city3")));
        memberRepository.saveMember(new Member("inss4", "first4", "password", "tes4@test.be", new Address("city4")));
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

    @Test
    void givenValidMember_createBookLoan_returnBookLoanOutDTO() {
        Member givenMember = new Member("inss5", "first5", "password", "tes5@test.be", new Address("city5"));
        memberRepository.saveMember(givenMember);

        Book givenBook = bookRepository.getBookByISBN("isbn4");

        BookLoanOutDTO result = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/books/" + givenMember.getUserId() + "/" + givenBook.getIsbn() + "/lend")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(BookLoanOutDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getLoanID()).isNotNull();
        assertThat(result.getMemberID()).isEqualTo(givenMember.getUserId());
        assertThat(result.getBookISBN()).isEqualTo(givenBook.getIsbn());
        assertThat(result.getLoanDate()).isEqualTo(LocalDate.now());
        assertThat(result.getDueDate()).isEqualTo(LocalDate.now().plusDays(21));
    }


    @Test
    void givenValidBookLoanID_whenReturningBook_returnBookLoanInDTO() {
        Member givenMember = new Member("inss5", "first5", "password", "tes5@test.be", new Address("city5"));
        memberRepository.saveMember(givenMember);

        Book givenBook = bookRepository.getBookByISBN("isbn4");

        BookLoanOut bookLoanOut = new BookLoanOut(givenMember.getUserId(), givenBook.getIsbn());
        bookLoanRepository.lendOutBook(bookLoanOut);

        BookLoanInDTO result = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/books/" + bookLoanOut.getLoanID() + "/return")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(BookLoanInDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getLoanID()).isNotNull();
        assertThat(result.getMemberID()).isEqualTo(givenMember.getUserId());
        assertThat(result.getBookISBN()).isEqualTo(givenBook.getIsbn());
        assertThat(result.getReturnDate()).isEqualTo(LocalDate.now());
        assertThat(result.getMessage()).isEqualTo("Thank you for being a responsible adult");
        assertThat(result.getFee()).isEqualTo(0.0);
    }

    @Test
    void givenInvalidBookLoanID_whenReturningBook_returnBadRequest() {
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/books/" + "invalidLoanID" + "/return")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenInvalidISBN_whenGettingBookDetails_returnBadRequest() {
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/books/invalidISBN")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenInvalidUserID_whenLendingBook_returnBadRequest() {
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/books/invalidUserID/isbn1/lend")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
