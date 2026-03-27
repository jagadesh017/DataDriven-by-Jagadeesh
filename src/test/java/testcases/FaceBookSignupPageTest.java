package testcases;

import base.DriverInstance;
import listeners.WebDriverEvent;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.BasicCL;
import pages.FacebookSignupPage;
import pages.HomePage;
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

    // Listener calling

    public void callingListener(String url) {

        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);

        WebDriverEvent listener = new WebDriverEvent();
        eventDriver.register(listener);
        eventDriver.navigate().to(url);
    }
@Test
    public void testDropdownValues(){

        BasicCL b = new BasicCL(driver);
        //b.selectDropdownValueslist();
        //b.printHoverMenuValues();
        //b.priceOfTheCourse();
    //b.citiesAndTheirSum();

    HomePage homePage = new HomePage(driver);
    homePage.webTableAgeCalculation();
    homePage.printAllOfficeNames();
    }
}
