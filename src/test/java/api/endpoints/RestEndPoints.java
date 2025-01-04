package api.endpoints;

import api.payloads.RestAPIPractice;
import com.github.fge.msgsimple.bundle.PropertiesBundle;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class RestEndPoints {
    public static String bearerToken = "72ba1d2c98a38b63668f1de75fe1323dc3baa1d9b4d255fa4076370abd6e4e7e";

    public static ResourceBundle getURL() {
        return ResourceBundle.getBundle("config");
    }

    public static Response CreateUser(RestAPIPractice getData) {

        System.out.println(getURL().getString("baseURLGOREST"));
        return given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(getData)
                .when()
                .post(Routes.postURLGOREST);
    }

    public static Response GetUserDetail(int id) {
        return given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(Routes.getURLGOREST);
    }

    public static Response UpdateUserDetail(RestAPIPractice getData, int id) {
        return given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(getData).pathParam("id", id).when().put(Routes.updateURLGOREST);
    }

    public static Response DeleteUserDetail(int id) {
        return given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").pathParam("id", id).when().delete(Routes.deleteURLGOREST);
    }
}
