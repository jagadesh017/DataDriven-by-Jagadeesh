package testcases;

import base.DriverInstance;
import listeners.WebDriverEvent;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.FacebookLoginPage;
import pages.FacebookSignupPage;
import testdatagenerators.DataGenerators;

import java.io.IOException;

public class FaceBookSignupPageTest extends DriverInstance {

    @Test
    public void FacebookLoginPageTest() throws InterruptedException, IOException {
        Reporter.log("FacebookLoginPageTest");
        Reporter.log("1. Open Facebook login page | Login page should be displayed");
        Reporter.log("2. Enter valid username and password | User should be able to login successfully");


        Reporter.log("Actual Result");
        Reporter.log("----------------------------");

        FacebookSignupPage signup = new FacebookSignupPage(driver);
        signup.clickSignUP();
        signup.enterFirstName("test");
        signup.enterLastNAme("user");
        signup.getDropdownOptions("May");


    }


    @Test(dataProvider = "Register", dataProviderClass = DataGenerators.class)
    public void test_signup_newUser() throws IOException {

        Reporter.log("Testcase  Description | expected results");
        Reporter.log("===============================");
        Reporter.log("1. Launch application | Application should be launched");
        Reporter.log("2. Verify url | URl should be displayed");
        Reporter.log("3. Click on create new account | Sign up form should be displayed");
        Reporter.log("4. Enter user details and click on sign up | user should be registered successfully");


        Reporter.log("Actual results");
        Reporter.log("--------------------");

        FacebookSignupPage signup = new FacebookSignupPage(driver);
        signup.clickSignUP();
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

    // Listener calling

    public void callingListener(String url) {

        EventFiringWebDriver eventDriver= new EventFiringWebDriver(driver);

        WebDriverEvent listener= new WebDriverEvent();
        eventDriver.register(listener);
        eventDriver.navigate().to(url);
    }
}
