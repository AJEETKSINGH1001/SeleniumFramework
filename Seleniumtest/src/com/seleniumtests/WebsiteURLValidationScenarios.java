package com.seleniumtests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class WebsiteURLValidationScenarios {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ajeet\\Documents\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testValidWebsiteURL() {
        String expectedURL = "https://www.flipkart.com/";
        driver.get(expectedURL);
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "URL mismatch");
    }

    @Test
    public void testInvalidWebsiteURL() {
        String expectedURL = "https://www.flipkart.com/";
        driver.get("https://www.flipkarts.com");
        String actualURL = driver.getCurrentUrl();
        Assert.assertNotEquals(actualURL, expectedURL, "URLs should not match");
    }

    @Test
    public void testURLWithQueryString() {
        String expectedURL = "https://www.flipkart.com/search?q=phone";
        driver.get(expectedURL);
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "URL mismatch");
    }

    @Test
    public void testURLWithHashFragment() {
        String expectedURL = "https://www.flipkart.com#section";
        driver.get(expectedURL);
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "URL mismatch");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
