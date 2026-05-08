package commonIQ;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AllInOneTest {

    public static WebDriver driver;

    WebElement element;

    @BeforeMethod
    public void launchBrowser() throws InterruptedException {

        driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void ExplicitWaitMethod() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.cssSelector("a[aria-label='Amazon.in']"));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Test
    public void DropdownOptionsSelection() {
        element = driver.findElement(By.cssSelector("select.nav-search-dropdown"));
        Select sel = new Select(element);
        // sel.selectByValue("Deals");
        List<WebElement> list = sel.getOptions();
        System.out.println(list.size());
        for (WebElement el : list) {
            System.out.println(el.getText());
            if (el.getText().equals("Deals")) {
                el.click();
                System.out.println("clicked Deals");
                break;
            }
        }
        //sel.selectByIndex(0);
        //sel.selectByVisibleText("abc");
        //sel.getOptions();
    }

    @Test
    public void mouseHover() {
        element = driver.findElement(By.cssSelector("div#nav-link-accountList"));
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
        String text = driver.findElement(By.xpath("//span[text()='Your Account']")).getText();
        System.out.println(text);
    }

    @Test
    public void alertTest() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        //alert.accept();
        //alert.getText();
    }

    @Test
    public void screenShotForPageAndElement() throws IOException {
        element = driver.findElement(By.cssSelector("a[aria-label='Amazon.in']"));
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file, new File("./src/test/java/resources/page.png"));

        File file1 = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file1, new File("./src/test/java/resources/element.png"));
    }

    @Test
    public void windowsHandling() throws InterruptedException {
        element = driver.findElement(By.cssSelector("input#twotabsearchtextbox"));
        element.sendKeys("Samsung galaxy s26 ultra");
        element.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("h2[aria-label*='Sponsored Ad - Galaxy S26 Ultra 5G (Black']")).click();
        Thread.sleep(2000);
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();
        String parentWindow = itr.next();
        String childWindow = itr.next();

        driver.switchTo().window(childWindow);
        String child_windowTitle = driver.getTitle();
        System.out.println("child_windowTitle:" + child_windowTitle);
        String version = driver.findElement(By.xpath("//span[text()='Android 16']")).getText();
        String androidVersion = version.replaceAll("[^0-9]", "");
        System.out.println("version:" + androidVersion);

        driver.switchTo().window(parentWindow);
        String parent_windowTitle = driver.getTitle();
        System.out.println("parent_windowTitle:" + parent_windowTitle);
    }

    @Test
    public void brokenLinks() {
        try {
            driver.get("https://google.com");
          List<WebElement> links= driver.findElements(By.tagName("a"));

          for(WebElement el : links){
            String url=  el.getAttribute("href");

            HttpsURLConnection connection =  (HttpsURLConnection) new URL(url).openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Valid link: " + url + " - Response code: " + responseCode);
            } else{
                System.out.println("Invalid link: " + url + " - Response code: " + responseCode);
            }


          }

    }catch (Exception e){
            Assert.fail("failed load broken links");
        }
    }
}
