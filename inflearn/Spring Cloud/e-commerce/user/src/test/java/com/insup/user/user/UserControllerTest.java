package com.insup.user.user;

import com.insup.user.AcceptanceTest;
import com.insup.user.user.dto.LoginRequest;
import com.insup.user.user.dto.UserRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends AcceptanceTest {

    static final String USER_ID = "jis1218";
    static final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        //given
        UserRequest userRequest = new UserRequest(
                USER_ID, "email@email.com", "insup", PASSWORD
        );
        //when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userRequest)
                .when()
                .post("/users/")
                .then().log().all().extract();

        //then


    }

    @Test
    @DisplayName("login test")
    void loginTest() {
        //given
        LoginRequest loginRequest = new LoginRequest(
                USER_ID, PASSWORD
        );
        //when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginRequest)
                .when()
                .post("/users/login")
                .then().log().all().extract();

        //then
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("login test fail")
    void loginTestFail() {
        //given
        LoginRequest loginRequest = new LoginRequest(
                USER_ID, PASSWORD + "hello"
        );
        //when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginRequest)
                .when()
                .post("/users/login")
                .then().log().all().extract();

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
