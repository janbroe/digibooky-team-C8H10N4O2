package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.Role;
import com.switchfully.digibooky.domain.users.User;
import com.switchfully.digibooky.domain.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = SecurityControllerIntegrationTest.class)
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
