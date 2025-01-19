package api.test;

import PageObjectRepository.LoginPageObjects;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginPageTest {
    private static WebDriver driver;
    private final static String webDriverPath = System.getProperty("user.dir") + "/ZDrivers/chromedriver.exe";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        driver = new ChromeDriver();
    }

    @Test
    public void loginPageCheck() throws IOException {
        LoginPageObjects loginPageObjects = new LoginPageObjects(driver);
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
        loginPageObjects.setUsername("student");
        loginPageObjects.setPassword("Password23");
        loginPageObjects.clickElement();

        String outputmessage = driver.findElement(By.xpath("//h1[@class='post-title']")).getText();

        if (outputmessage.equals("Logged In Successfully")) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File srcPath = takesScreenshot.getScreenshotAs(OutputType.FILE);

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formattedDateTime = currentDateTime.format(formatter);

            File desPath = new File(System.getProperty("user.dir") + "/src/ZScreenshots/" + formattedDateTime + ".png");
            FileUtils.copyFile(srcPath, desPath);
            System.out.println("FAILED: Screenshot attached!!!");
        }
        else {
            System.out.println("justin");
        }

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}