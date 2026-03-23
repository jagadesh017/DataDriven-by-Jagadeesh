package testcases;

import base.DriverInstance;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.FacebookLoginPage;


public class FacebookLoginPageTest extends DriverInstance {

    @Test
    public void testFacebookLoginPageTest() throws InterruptedException {
        Reporter.log("FacebookLoginPageTest");
        Reporter.log("1. Open Facebook login page | Login page should be displayed");
        Reporter.log("2. Enter valid username and password | User should be able to login successfully");


        Reporter.log("Actual Result");
        Reporter.log("----------------------------");

        FacebookLoginPage loginPage = new FacebookLoginPage(driver);
        Thread.sleep(3000);
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("testpassword");
        loginPage.clickSignin();

    }
}
