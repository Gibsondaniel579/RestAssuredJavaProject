package api.test;

import api.endpoints.RestEndPoints;
import api.payloads.RestAPIPractice;
import api.utilities.DataProviders;
import com.mifmif.common.regex.util.Iterator;
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

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
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

//    @Test
//    public void printNumbers() {
////1 23 456 7891
//        int row = 5;
//        StringBuilder entry = new StringBuilder();
//        int k = 1;
//        for (int i = 1; i <= row; i++) {
//            for (int j = 0; j < i; j++) {
//                entry.append(k);
//                k++;
//                if (k > 9) {
//                    k = 1;
//                }
//            }
//            if (i < row) {
//                entry.append(" "); // Add space between rows
//            }
//        }
//        System.out.println(entry.toString());
//    }
//
//    @Test
//    public void StringReverse() {
//
//        String sentence = "Automation is fun";
//        String reversedSentence = "";
//        String[] words = sentence.split(" ");
//        for (int i = words.length - 1; i >= 0; i--) {
//            reversedSentence = reversedSentence + words[i] + " ";
//        }
////        reversedSentence.trim();
//        System.out.println(reversedSentence);
//
//    }
}
