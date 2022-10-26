package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.UserRepository;
import com.switchfully.digibooky.service.users.UserMapper;
import com.switchfully.digibooky.service.users.dto.CreateUserDTO;
import com.switchfully.digibooky.service.users.dto.UserDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = MemberControllerIntegrationTest.class)
class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final UserMapper userMapper = new UserMapper();

    @Autowired
    private UserRepository userRepository;

    @Test
    void createAMember() {
        CreateUserDTO given = new CreateUserDTO()
                .setInss("testInss")
                .setFirstname("testFirstName")
                .setLastname("testLastName")
                .setPassword("password")
                .setEmail("test@test.test")
                .setAddress(new Address("testCity"));

        UserDTO result = RestAssured
                .given()
                .body(given)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getFirstname()).isEqualTo(given.getFirstname());
        assertThat(result.getLastname()).isEqualTo(given.getLastname());
        assertThat(result.getEmail()).isEqualTo(given.getEmail());
        assertThat(result.getAddress()).isEqualTo(given.getAddress());
    }
}