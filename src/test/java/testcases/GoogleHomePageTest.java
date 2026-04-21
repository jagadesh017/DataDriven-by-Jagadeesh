package testcases;

import base.DriverInstance;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.GoogleHomePage;

public class GoogleHomePageTest extends DriverInstance {

    @Test
    public void testGoogleHomePage() throws InterruptedException{
        Reporter.log("Starting test: testGoogleHomePage");

        Reporter.log("Actual results");

        GoogleHomePage homepage = new GoogleHomePage(driver);
        homepage.verifyPageTitle();
        homepage.verifyPageTitle("Google");
        homepage.enterSearchQuery("Selenium WebDriver");
        Reporter.log("Text entered successfully in search field");
        Thread.sleep(2000);
        homepage.enterTextInSearchField("playwright");
        homepage.enterValueInSearchField(12345);
        Thread.sleep(2000);
        homepage.clickEnter();
        Thread.sleep(2000);
        Reporter.log("Test completed: testGoogleHomePage");
        homepage.verifyPageUrlContains("google");



    }
}
