package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
        // Define the file path to your Excel file
        String path = System.getProperty("user.dir") + "//src//TestDataFolder//TestData.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int row = xlUtility.getRowCount("sheet1");
        int column = xlUtility.getCellCount("sheet1", 1);

        Object[][] apidata = new Object[row][column]; // Object[][] for compatibility with TestNG

        // Populate the two-dimensional array with Excel data
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < column; j++) {
                apidata[i - 1][j] = xlUtility.getCellData("sheet1", i, j); // i - 1 for zero-based indexing
            }
        }
        return apidata;
    }


    @DataProvider(name = "getNames")
    public Iterator<Object[]> getUserName() throws IOException {
        String path = System.getProperty("user.dir") + "//src//TestDataFolder//TestData.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int row = xlUtility.getRowCount("sheet1");
        List<Object[]> userNamesList = new ArrayList<>();

        for (int i = 1; i <= row; i++) {
            String userName = xlUtility.getCellData("sheet1", i, 2);
            userNamesList.add(new Object[]{userName}); // Wrap each username in an Object[]
        }

        return userNamesList.iterator();
    }
}
