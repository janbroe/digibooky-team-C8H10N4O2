package com.switchfully.digibooky.domain.bookLoans;

import com.switchfully.digibooky.domain.books.Author;
import com.switchfully.digibooky.domain.books.Book;
import com.switchfully.digibooky.domain.books.BookRepository;
import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Role;
import com.switchfully.digibooky.domain.users.User;
import com.switchfully.digibooky.domain.users.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookLoanRepositoryTest {

    //TODO init all repos in @BeforeEach method

    @Test
    void lendOutBookHappyPath() {
        //given
        BookRepository bookRepository = new BookRepository();
        Book lendBook = new Book("isbn6", "title6", new Author("first6", "last6"));
        bookRepository.saveBook(lendBook);

        UserRepository userRepository = new UserRepository();
        User lendUser = new User("inss6", "first6", "password", "tes6@test.be", new Address("city6"), Role.MEMBER);
        userRepository.saveMember(lendUser);

        BookLoanRepository bookLoanRepository = new BookLoanRepository(bookRepository, userRepository);

        //Only thing that can be tested now since we can't get a list of all the lend books yet.
        assertThat(bookRepository.getBookByISBN(lendBook.getIsbn()).isAvailable()).isTrue();

        bookLoanRepository.lendOutBook(new BookLoanOut(lendUser.getUserId(), lendBook.getIsbn()));

        assertThat(bookRepository.getBookByISBN(lendBook.getIsbn()).isAvailable()).isFalse();
    }

    @Test
    void givenBookLoanWithExistingMember_whenLendOutBook_ThenGetNoSUchElementException() {
        //given
        BookRepository bookRepository = new BookRepository();
        Book lendBook = new Book("isbn6", "title6", new Author("first6", "last6"));
        bookRepository.saveBook(lendBook);

        UserRepository userRepository = new UserRepository();

        BookLoanRepository bookLoanRepository = new BookLoanRepository(bookRepository, userRepository);

        BookLoanOut givenBookLoan = new BookLoanOut("WrongMemberID", lendBook.getIsbn());

        assertThatThrownBy(() -> bookLoanRepository.lendOutBook(givenBookLoan))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Member with ID ".concat(givenBookLoan.getMemberID()).concat(" does not exist"));

    }

    @Test
    void givenBookLoanWithExistingBook_whenLendOutBook_ThenGetNoSUchElementException() {
        //given
        BookRepository bookRepository = new BookRepository();
        Book lendBook = new Book("isbn6", "title6", new Author("first6", "last6"));
        bookRepository.saveBook(lendBook);

        UserRepository userRepository = new UserRepository();
        User lendUser = new User("inss6", "first6", "password", "tes6@test.be", new Address("city6"), Role.MEMBER);
        userRepository.saveMember(lendUser);

        BookLoanRepository bookLoanRepository = new BookLoanRepository(bookRepository, userRepository);

        BookLoanOut givenBookLoan = new BookLoanOut(lendUser.getUserId(), lendBook.getIsbn());

        //lend book so that it is not available anymore
        bookLoanRepository.lendOutBook(givenBookLoan);

        assertThatThrownBy(() -> bookLoanRepository.lendOutBook(givenBookLoan))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Book with ISBN ".concat(givenBookLoan.getBookISBN()).concat(" is not available."));
    }

    @Test
    void returnBookHappyPath() {
        //given
        BookRepository bookRepository = new BookRepository();
        Book lendBook = new Book("isbn6", "title6", new Author("first6", "last6"));
        bookRepository.saveBook(lendBook);

        UserRepository userRepository = new UserRepository();
        User lendUser = new User("inss6", "first6", "password", "tes6@test.be", new Address("city6"), Role.MEMBER);
        userRepository.saveMember(lendUser);

        BookLoanRepository bookLoanRepository = new BookLoanRepository(bookRepository, userRepository);

        BookLoanOut givenBookLoanOut = new BookLoanOut(lendUser.getUserId(), lendBook.getIsbn());
        BookLoanIn givenBookLoanIn = new BookLoanIn(givenBookLoanOut.getMemberID(), givenBookLoanOut.getBookISBN());

        bookLoanRepository.lendOutBook(givenBookLoanOut);

        //Check that book is not available anymore
        assertThat(bookRepository.getBookByISBN(lendBook.getIsbn()).isAvailable()).isFalse();
        //return book
        bookLoanRepository.returnBook(givenBookLoanIn);
        //Check that book is back available
        assertThat(bookRepository.getBookByISBN(lendBook.getIsbn()).isAvailable()).isTrue();

    }

    @Test
    void givenNonExistingID_whenReturnBook_thenGetNoSuchElementException() {
        //given
        BookRepository bookRepository = new BookRepository();

        UserRepository userRepository = new UserRepository();

        BookLoanRepository bookLoanRepository = new BookLoanRepository(bookRepository, userRepository);


        assertThatThrownBy(() -> bookLoanRepository.getBookLoanOutByLoanID("NonExistingLendingID"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Book loan with ID NonExistingLendingID could not be found");

    }

}