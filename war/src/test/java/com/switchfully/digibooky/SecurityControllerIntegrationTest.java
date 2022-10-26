package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Role;
import com.switchfully.digibooky.domain.users.User;
import com.switchfully.digibooky.domain.users.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityControllerIntegrationTest {
    @LocalServerPort
    private int port;
    private static final String URI = "http://localhost";
    @Autowired
    UserRepository userRepository;
    private final User user = new User("testINSS", "testLast", "password", "user@test.be", new Address("city"), Role.MEMBER);

    @BeforeEach
    void clearRepositoryAndAddMember() {
        userRepository.getAll().clear();
        userRepository.saveMember(user);
    }

    @Test
    void login_givenValidEmailAndPassword() {
        String authorization = Base64.getEncoder().encodeToString("user@test.be:password".getBytes());

        RestAssured
                .given()
                .baseUri(URI)
                .port(port)
                .when()
                .headers("Authorization", "Basic " + authorization)
                .get("/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void login_givenInValidEmail() {
        String authorization = Base64.getEncoder().encodeToString("not@valid.email:password".getBytes());

        RestAssured
                .given()
                .baseUri(URI)
                .port(port)
                .when()
                .headers("Authorization", "Basic " + authorization)
                .get("/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void login_givenInValidPassword() {
        String authorization = Base64.getEncoder().encodeToString("user@test.be:invalidPassword".getBytes());

        RestAssured
                .given()
                .baseUri(URI)
                .port(port)
                .when()
                .headers("Authorization", "Basic " + authorization)
                .get("/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
