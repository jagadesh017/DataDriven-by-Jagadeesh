package commonIQ;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ScreenshotForElement {
    private static final Logger log = LoggerFactory.getLogger(ScreenshotForElement.class);

    public static void main(String[] args) throws IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

       WebElement logo = driver.findElement(By.cssSelector("svg[fill='currentColor']"));

        File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file,new File("./src/java/resources/screenshot.png"));

         driver.quit();
    }

}
