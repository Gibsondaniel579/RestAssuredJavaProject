package api.mockapidb;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CheckAPIWithDB {
    @Test
    public void checkUserRequest() {
        String jsonRequest = "{\n" +
                "  \"firstname\": \"Doen\",\n" +
                "  \"lastname\": \"Doe\",\n" +
                "  \"room\": \"555\"\n" +
                "}";

        given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("http://localhost:3000/bookings")
                .then()
                .log().all();

    }

    @Test
    public void getUserDetails() {

        given()
                .when()
                .get("http://localhost:3000/bookings")
                .then()
                .log().all();
    }
}
