package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static Response CreateUser(User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.postURL);
    }

    public static Response ReadUser(String userName) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .log().uri()
                .when()
                .get(Routes.getURL);
    }

    public static Response UpdateUser(String userName, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.updateURL);
    }

    public static Response DeleteUser(String userName) {
        return given()
                .pathParam("userName", userName)
                .when()
                .delete(Routes.deleteURL);
    }
}
