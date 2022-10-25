package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Member;
import com.switchfully.digibooky.domain.users.MemberRepository;
import com.switchfully.digibooky.service.books.dto.BookDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityControllerIntegrationTest {
    @LocalServerPort
    private int port;
    private static final String URI = "http://localhost";
    @Autowired
    MemberRepository memberRepository;
    private final Member member = new Member("testINSS", "testLast", "password", "user@test.be", new Address("city"));

    @BeforeEach
    void clearRepositoryAndAddMember() {
        memberRepository.getAll().clear();
        memberRepository.saveMember(member);
    }

//    @Test
//    void login_givenValidEmailAndPassword() {
//
//        RestAssured
//                .given()
//                .baseUri(URI)
//                .port(port)
//                .when()
//                .auth()
//                .basic(member.getEmail(), member.getPassword())
//                .get("/login")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value());
//    }

//    @Test
//    void login_givenInValidEmail() {
//        RestAssured
//                .given()
//                .baseUri(URI)
//                .port(port)
//                .when()
//                .auth()
//                .basic("not@valid.email", member.getPassword())
//                .get("/login")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.FORBIDDEN.value());
//    }

//    @Test
//    void login_givenInValidPassword() {
//        RestAssured
//                .given()
//                .baseUri(URI)
//                .port(port)
//                .when()
//                .auth()
//                .basic("not@valid.email", member.getPassword())
//                .header()
//                .get("/login")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.FORBIDDEN.value());
//    }
}
