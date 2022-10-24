package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.users.Address;
import com.switchfully.digibooky.domain.users.MemberRepository;
import com.switchfully.digibooky.service.users.MemberMapper;
import com.switchfully.digibooky.service.users.dto.CreateMemberDTO;
import com.switchfully.digibooky.service.users.dto.MemberDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final MemberMapper memberMapper = new MemberMapper();

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createAMember() {
        CreateMemberDTO given = new CreateMemberDTO()
                .setInss("testInss")
                .setFirstname("testFirstName")
                .setLastname("testLastName")
                .setEmail("test@test.test")
                .setAddress(new Address("testCity"));

        MemberDTO result = RestAssured
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
                .as(MemberDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getFirstname()).isEqualTo(given.getFirstname());
        assertThat(result.getLastname()).isEqualTo(given.getLastname());
        assertThat(result.getEmail()).isEqualTo(given.getEmail());
        assertThat(result.getAddress()).isEqualTo(given.getAddress());
    }
}