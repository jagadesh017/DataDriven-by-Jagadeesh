package testcases;

import base.DriverInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import pages.BasicPracticePage;

public class BasicTestClass extends DriverInstance {

    @org.testng.annotations.Test
    public void testBasic() {
        Reporter.log("BasicTestClass");
        Reporter.log("1. Open application | Application should be opened");
        Reporter.log("2. Verify title | Title should be correct");
        Reporter.log("3. Verify URL | URL should be correct");

        Reporter.log("Actual Result");
        Reporter.log("----------------------------");
        BasicPracticePage page = new BasicPracticePage(driver);
       page.validateBrokenLinks();
    }
}
