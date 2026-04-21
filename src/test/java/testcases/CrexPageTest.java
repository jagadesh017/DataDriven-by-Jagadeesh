package testcases;

import base.DriverInstance;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CrexPage;

@Listeners (listeners.TestNGListener.class)
public class CrexPageTest extends DriverInstance{


    @Test
    public void testTheIPL2026TableValues(){
        CrexPage crex = new CrexPage(driver);
        crex.getIPL2026Table();
    }

    @Test
    public void testRohitScoreFromTheTable(){
        CrexPage crex = new CrexPage(driver);
        crex.checkRohitScoreFromTheTable();
        crex.checkRohitScoreFromTheTable();
    }
}
