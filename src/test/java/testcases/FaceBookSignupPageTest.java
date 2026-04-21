package testcases;

import base.DriverInstance;
import listeners.WebDriverEvent;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.BasicCL;
import pages.FacebookSignupPage;
import testdatagenerators.DataGenerators;

import java.io.IOException;

public class FaceBookSignupPageTest extends DriverInstance {

    @Test
    public void FacebookSinUpPageTest() throws InterruptedException, IOException {
        Reporter.log("FacebookLoginPageTest");
        Reporter.log("1. Open Facebook login page | Login page should be displayed");
        Reporter.log("2. Click on sign up button | Sign up form should be displayed");
        Reporter.log("3. Enter First name and last name | First name and last name should be entered");
        Reporter.log("4. enter date of birth | date of birth should be entered");

        Reporter.log("Actual Result");
        Reporter.log("----------------------------");
        FacebookSignupPage signupPage = new FacebookSignupPage(driver);
        signupPage.clickSignUP();
        signupPage.enterFirstName("John");
        signupPage.enterLastNAme("Doe");
        signupPage.getDropdownOptions("Mar");

    }
    
    @Test
    public void testVerifyMetaTag() throws IOException {

        Reporter.log("Testcase  Description | expected results");
        Reporter.log("===============================");
        Reporter.log("1. Launch application | Application should be launched");
        Reporter.log("2. Verify url | URl should be displayed");
        Reporter.log("3. Click on create new account | Sign up form should be displayed");
        Reporter.log("4. Enter user details and click on sign up | user should be registered successfully");

        Reporter.log("Actual results");
        Reporter.log("--------------------");
        FacebookSignupPage signupPage = new FacebookSignupPage(driver);
        signupPage.clickSignUP();
        signupPage.verifyMetaTag();
        signupPage.getMetaText();
    }

    @Test(dataProvider = "Register", dataProviderClass = DataGenerators.class)
    public void tc_002_newUser_2(String fName, String lName, String num) throws IOException {

        Reporter.log("Testcase  Description | expected results");
        Reporter.log("===============================");
        Reporter.log("1. Launch application | Application should be launched");
        Reporter.log("2. Verify url | URl should be displayed");
        Reporter.log("3. Enter username and password | username and password should be entered");
        Reporter.log("4. Click on login button | user should be logged in");
    }

    // Listener calling - Updated for Selenium 4
    public void callingListener(String url) {
        // In Selenium 4, listeners are registered via WebDriver manager
        // This is a placeholder method as EventFiringWebDriver was deprecated
        WebDriverEvent listener = new WebDriverEvent();
        driver.navigate().to(url);
        // Listeners would be registered through the driver configuration, not at runtime
    }
    }

