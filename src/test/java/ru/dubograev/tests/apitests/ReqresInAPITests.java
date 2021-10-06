package ru.dubograev.tests.apitests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@Tag("API")
public class ReqresInAPITests {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @DisplayName("List of users")
    public void listUsersGetTest() {
        when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .log().body()
                .body("page", equalTo(2), "total", equalTo(12));
    }

    @Test
    @DisplayName("ID should be equal 2 and email should be 'janet.weaver@reqres.in'")
    public void singleUserGetTest() {
        when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .assertThat().body("data.id", equalTo(2), "data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Single user should not be found")
    public void singleUserNotFoundTest() {
        when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .log().body()
                .body("isEmpty()", is(true));
    }

    @Test
    @DisplayName("LIST <RESOURCE> test1")
    public void listResourceGetTest() {
        when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .assertThat().body("data.id", hasItems(1 , 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("LIST <RESOURCE> test2")
    public void listResourceGetDataSizeTest() {
        when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .assertThat().body("data", hasSize(6));
    }

    @Test
    @DisplayName("LIST <RESOURCE> test3")
    public void listResourceGetContainsNameTest() {
        when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .assertThat().body("data[0].name", equalTo("cerulean"))
                .assertThat().body("data.name", hasItem("aqua sky"));
    }

    @Test
    @DisplayName("Create a user with name 'morpheus' and job title 'leader'")
    public void createUserViaPostTest() {
        String data = "{ \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body()
                .body("name",is("morpheus"))
                .body("job",is("leader"));
    }

    @Test
    @DisplayName("Put test")
    public void updateUserViaPutTest() {
        given()
                .contentType(JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name",is("morpheus"))
                .body("job",is("zion resident"));
    }
}