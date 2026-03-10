package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utility.Utilities;

import java.io.IOException;

public class DriverInstance {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        if(Utilities.fetchPropertyFile("browser").equals("chrome")){
             System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
             driver = new ChromeDriver();

        } else
        if(Utilities.fetchPropertyFile("browser").equals("firefox")){

                driver = new FirefoxDriver();
        } else {
            driver = new InternetExplorerDriver();

        }

            driver.get("https://www.facebook.com");
            driver.manage().window().maximize();


    }

  //  @AfterMethod
    public void tearDown() {
        driver.quit();
        }

}
