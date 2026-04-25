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
import java.lang.reflect.Method;

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

//@BeforeMethod
@Override
public void navigateTo() throws IOException {
    if(Utilities.fetchPropertyFile("google").equals("https://www.google.com")) {
        driver.get("https://www.google.com");
    } else if(Utilities.fetchPropertyFile("facebook").equals("https://www.facebook.com")) {
        driver.get("https://www.facebook.com");
    } else if(Utilities.fetchPropertyFile("practice").equals("https://rahulshettyacademy.com/AutomationPractice/")) {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    } else if(Utilities.fetchPropertyFile("datatables").equals("https://datatables.net/")) {
        driver.get("https://datatables.net/");
        } else if(Utilities.fetchPropertyFile("crex").equals("https://crex.com/")) {
        driver.get("https://crex.com/");
    }
    driver.manage().window().maximize();
}

    @BeforeMethod
    public void navigateTo(Method method) throws IOException {
        // This gets the name of the class where the @Test is located
        String className = method.getDeclaringClass().getSimpleName();

        // Fetch the URL from properties using the class name as the key
        Object url = Utilities.fetchPropertyFile(className);

        if (url != null) {
            driver.get(url.toString());
            driver.manage().window().maximize();
        } else {
            System.out.println("No URL found for class: " + className);
        }
    }

}
