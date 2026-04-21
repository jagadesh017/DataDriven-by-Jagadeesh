package testcases;

import base.DriverInstance;
import org.testng.annotations.Test;
import pages.BasicCL;

public class BasicCLTest extends DriverInstance {

    @Test
    public void testAllScenariosInSingleTest(){

        BasicCL basic = new BasicCL(driver);
     /*   basic.selectDropdownValuesList(3);
        basic.printHoverMenuValues();
        basic.priceOfTheCourse();
        basic.citiesAndTheirSum();
        basic.getAlertMessage();
        basic.switchWindowFromParentToChild();
        basic.autoSuggestionCapture();
        basic.openNewTab();
        basic.iframe(); */
        basic.checkDoller();



    }
}
