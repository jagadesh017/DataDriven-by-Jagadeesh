package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.Utilities;

import java.io.IOException;

public class DriverInstance {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        if(Utilities.fetchPropertyFile("browser").equals("chrome")){
            if(Utilities.fetchPropertyFile("compatibility").equals("mac")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else {
            System.setProperty("webdriver.chrome.driver", "/Users/jagadeesh/JagadeeshAuto/DataDriven/Maven/Drivers/chromedriver");
                driver = new ChromeDriver();
            }
        } else
        if(Utilities.fetchPropertyFile("browser").equals("firefox")){

                driver = new FirefoxDriver();
        } else {
            driver = new InternetExplorerDriver();

        }
          //  driver.get("https://www.facebook.com");
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //driver.get("https://datatables.net/");
        driver.manage().window().maximize();


    }

  @AfterMethod
    public void tearDown() {
        driver.quit();
        }

}
