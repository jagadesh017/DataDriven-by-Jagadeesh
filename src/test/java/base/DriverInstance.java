package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import common.Utilities;

import java.io.IOException;

public class DriverInstance implements DriverActions {

    protected WebDriver driver;

    @BeforeMethod
    public void browserLaunch() {
        try {
            if (Utilities.fetchPropertyFile("browser").equals("chrome")) {
                if (Utilities.fetchPropertyFile("compatibility").equals("mac")) {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                } else {
                    System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver147.exe");
                    driver = new ChromeDriver();
                }
            } else if (Utilities.fetchPropertyFile("browser").equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterMethod
    @Override
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    @Override
    public void navigateTo() {
        //driver.get("https://www.google.com");
        driver.get("https://www.facebook.com");
        //driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //driver.get("https://datatables.net/");
       // driver.get("https://crex.com/");
        driver.manage().window().maximize();
    }
}
