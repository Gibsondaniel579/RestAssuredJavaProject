package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    public static Faker faker;
    public User userData;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userData = new User();

        userData.setId(faker.idNumber().hashCode());
        userData.setUserName(faker.name().username());
        userData.setFirstName(faker.name().firstName());
        userData.setLastName(faker.name().lastName());
        userData.setEmail(faker.internet().emailAddress());
        userData.setPassword(faker.internet().password());
        userData.setPhone(faker.phoneNumber().cellPhone());
        userData.setUserStatus(1);
    }

    @Test(priority = 1)
    public void sendUserRequest() {
        Response response = UserEndpoints.CreateUser(this.userData);
        System.out.println(userData.getUserName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void getUserNameCheck() throws InterruptedException {
        final int MAX_RETRIES = 5;
        // Delay between retries in milliseconds
        final int RETRY_DELAY_MS = 5000;
        int attempt = 5;
        System.out.println(userData.getUserName());
        Response response = UserEndpoints.ReadUser(this.userData.getUserName());
        response.then().log().all();
        while (response.getStatusCode() == 404 && attempt > 0) {
            System.out.println("Resource not found. Retrying...");

            if (attempt > 0) {
                System.out.println("Retrying in " + RETRY_DELAY_MS / 1000 + " seconds...");
                Thread.sleep(RETRY_DELAY_MS);  // Wait before retrying

                // Retry the GET request
                response = UserEndpoints.ReadUser(this.userData.getUserName());
                response.then().log().all();  // Log the response
                attempt--;
            }
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void updateUserData() {
        userData.setFirstName(faker.name().firstName());
        userData.setLastName(faker.name().lastName());
        userData.setEmail(faker.internet().emailAddress());

        Response response = UserEndpoints.UpdateUser(this.userData.getUserName(), userData);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
