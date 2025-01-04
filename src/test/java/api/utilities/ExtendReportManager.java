package api.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReportManager implements ITestListener {

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    String repName = "";

    public void onStart(ITestContext testcontext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timestamp + ".html";

        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/Reports/" + repName);
        extentSparkReporter.config().setDocumentTitle("RESTAssured Automation Reports");
        extentSparkReporter.config().setReportName("GOREST API");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Application", "GOREST API");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Gibson Daniel G");
    }

    public void onTestSuccess(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
        File f = new File(screenshotPath);
        if (f.exists()) {
            logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
        }
    }

    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName());
        logger.createNode(tr.getName());
        logger.assignCategory(tr.getMethod().getGroups());
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
