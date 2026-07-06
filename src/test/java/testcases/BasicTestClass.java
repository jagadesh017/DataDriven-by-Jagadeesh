package testcases;

import base.DriverInstance;
import org.testng.Reporter;
import commonIQ.BrokenLinks;

public class BasicTestClass extends DriverInstance {

    @org.testng.annotations.Test
    public void testBasic() {
        Reporter.log("BasicTestClass");
        Reporter.log("S.No| Description | Expected Results");
        Reporter.log("------------------------------------");
        Reporter.log("1. Open application | Application should be opened");
        Reporter.log("2. Verify title | Title should be correct");
        Reporter.log("3. Verify URL | URL should be correct");

        Reporter.log("Actual Result");
        Reporter.log("----------------------------");
        BrokenLinks page = new BrokenLinks(driver);
       page.validateBrokenLinks();
    }
}
