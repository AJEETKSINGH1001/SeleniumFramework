package com.seleniumtests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reportwithdatetime {
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private String reportFileName;

    @BeforeMethod
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ajeet\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();

        // Generate a unique timestamp for the report file
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        reportFileName = "test-output/ExtentReport_" + timestamp + ".html";

        // Initialize ExtentReports with the dynamic file name
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFileName);
       // ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFileName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Test
    public void testValidWebsiteURL() {
        extentTest = extentReports.createTest("testValidWebsiteURL");
        extentTest.log(Status.INFO, "Test started");

        String expectedURL = "https://www.flipkart.com/";
        driver.get(expectedURL);
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "URL mismatch");

        extentTest.log(Status.PASS, "URL validation successful");
    }

    // ... other test methods ...

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "Test case failed: " + result.getName());
            extentTest.log(Status.FAIL, "Failure details: " + result.getThrowable());
        }

        if (extentReports != null) {
            extentReports.flush();
        }

        driver.quit();
    }
}
