package api.test;

import api.endpoints.RestEndPoints;
import api.payloads.RestAPIPractice;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GORESTTests {
    RestAPIPractice restAPIPractice;
    Faker faker;
    public int id;

    @BeforeTest
    public void dataSetup() {
        restAPIPractice = new RestAPIPractice();
        faker = new Faker();
        restAPIPractice.setName(faker.name().username());
        restAPIPractice.setEmail(faker.internet().emailAddress());
        restAPIPractice.setGender(faker.options().option("male", "female"));
        restAPIPractice.setStatus(faker.options().option("active", "inactive"));
    }

    @Test(priority = 1)
    public void CreateUserTest(ITestContext context) {
        Response response = RestEndPoints.CreateUser(restAPIPractice);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
        id = response.getBody().jsonPath().getInt("id");
        context.setAttribute("user_id", id);
    }

    @Test(priority = 2, dependsOnMethods = {"CreateUserTest"})
    public void GetUserDetails(ITestContext context) {
        id = (int) context.getAttribute("user_id");
        Response response = RestEndPoints.GetUserDetail(id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = {"CreateUserTest"})
    public void UpdateUserDetails(ITestContext context) {
        id = (int) context.getAttribute("user_id");
        System.out.println(id);
        restAPIPractice.setName(faker.name().username());
        restAPIPractice.setEmail(faker.internet().emailAddress());
        Response response = RestEndPoints.UpdateUserDetail(restAPIPractice, id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = {"CreateUserTest"})
    public void DeleteUSerDetails(ITestContext context) {
        id = (int) context.getAttribute("user_id");
        Response response = RestEndPoints.DeleteUserDetail(id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
