package testcases;

import base.DriverInstance;
import org.testng.annotations.Test;
import pages.BasicCL;

public class BasicCLTest extends DriverInstance {

    @Test
    public void testDropdownValues(){

        BasicCL basic = new BasicCL(driver);
     /*   basic.selectDropdownValuesList(3);
        basic.printHoverMenuValues();
        basic.priceOfTheCourse();
        basic.citiesAndTheirSum();
        basic.getAlertMessage(); */
        basic.switchWindowFromParentToChild();
        basic.autoSuggestionCapture();
        basic.iframe();
        basic.checkDoller();
        basic.openNewTab();

    }
}
