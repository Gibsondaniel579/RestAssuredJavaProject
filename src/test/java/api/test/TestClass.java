package api.test;

import api.utilities.DataProviders;
import org.testng.annotations.Test;

public class TestClass {

    //@Test(dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testUser(String username) {
        // Print the username received from the data provider
        System.out.println("Testing with username: " + username);
    }
}