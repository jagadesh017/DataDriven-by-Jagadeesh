package commonIQ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MouseHover {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        //driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement element=driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

        WebElement element1 =driver.findElement(By.xpath("//div[text()='Your Account']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf(element1));

        Assert.assertTrue(element1.isDisplayed(), "Element is not displayed");
        String text=element1.getText();
        System.out.println(text);
        driver.quit();
    }
}
