package api.test;

import api.endpoints.RestEndPoints;
import api.payloads.RestAPIPractice;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

public class DDTests {

    public Logger logger;

    @BeforeClass
    public void setConfig() {
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(dataProvider = "getNames", dataProviderClass = DataProviders.class, enabled = true)
    public void getUserNames(Object[] username) {
        System.out.println("getNames" + Arrays.toString(username));
        logger.warn("Verified");
        logger.info("Checked correctly!!!");
    }

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class, enabled = true)
    public void getAllData(String name, String email, String gender, String status) {
        System.out.println(name + "--" + email + "--" + gender + "--" + status);
        logger.warn("Checking correctly");
    }

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void sendUsers(String name, String email, String gender, String status) {
        RestAPIPractice restAPIPractice = new RestAPIPractice();
        restAPIPractice.setName(name);
        restAPIPractice.setEmail(email);
        restAPIPractice.setGender(gender);
        restAPIPractice.setStatus(status);

        Response response = RestEndPoints.CreateUser(restAPIPractice);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
        logger.info("API worked correctly!!!");

    }
}
