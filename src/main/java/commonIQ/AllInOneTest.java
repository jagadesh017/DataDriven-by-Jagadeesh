package commonIQ;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
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

import static org.testng.Assert.assertEquals;

public class AllInOneTest {

    public static WebDriver driver;
    public static JavascriptExecutor js;
    public static Actions actions;
    WebElement element;

    @BeforeMethod
    public void AllInOneTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
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
        driver.get("https://demo.automationtesting.in/Alerts.html");
        driver.findElement(By.cssSelector("[onclick='alertbox()']")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.dismiss();
        //alert.accept();

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
    public void scrollPage() {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @Test
    public void moveToPerticulorElementUsingJS() throws InterruptedException {
        WebElement element1 = driver.findElement(By.xpath("//a[text()='About Amazon']"));
        //js.executeScript("arguments[0].scrollIntoView(true);", element1);
        actions.moveToElement(element1).build().perform();
        Thread.sleep(2000);

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
            List<WebElement> links = driver.findElements(By.tagName("a"));

            for (WebElement el : links) {
                String url = el.getAttribute("href");

                HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Valid link: " + url + " - Response code: " + responseCode);
                } else {
                    System.out.println("Invalid link: " + url + " - Response code: " + responseCode);
                }


            }

        } catch (Exception e) {
            Assert.fail("failed load broken links");
        }
    }

    @Test
    public void fileUploadAndDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");
        element = driver.findElement(By.cssSelector("#file-upload"));
        Thread.sleep(2000);
        element.sendKeys("/Users/jagadeesh/Documents/test.txt");
        Thread.sleep(2000);
        driver.findElement(By.id("file-submit")).click();
        System.out.println("file uploaded successfully");


    }

    @Test
    public void webTable1() throws InterruptedException {
        driver.get("https://crex.com");
        driver.findElements(By.cssSelector("div.upcoming")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()=' Points Table ']")).click();

        Thread.sleep(2000);

        List<WebElement> rowCount = driver.findElements(By.cssSelector("div.points-table-wrapper tr"));
        List<WebElement> columnCount = driver.findElements(By.xpath("//div[@class='points-table-wrapper']//tr[2]//td"));
        for (int i = 2; i <= rowCount.size(); i++) {
            for (int j = 1; j < columnCount.size(); j++) {

                WebElement text = driver.findElement(By.xpath("//div[@class='points-table-wrapper']//tr[" + i + "]//td[" + j + "]"));
                System.out.print(text.getText() + "|");
            }
            System.out.println();
        }
    }

    @Test
    public void webTable2() throws InterruptedException {
        driver.get("https://crex.com");
        driver.findElements(By.cssSelector("div.upcoming")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()=' Points Table ']")).click();
        Thread.sleep(2000);
        List<WebElement> rowCount = driver.findElements(By.cssSelector("div.points-table-wrapper tr"));
        List<WebElement> columnCount = driver.findElements(By.xpath("//div[@class='points-table-wrapper']//tr[2]//td"));
        // print only team name and points

        //team name //div[@class='points-table-wrapper']//tr[2]//td[1]
        // points ////div[@class='points-table-wrapper']//tr[2]//td[8]

        for (int i = 2; i <= rowCount.size(); i++) {
            String teamName= driver.findElement(By.xpath("//div[@class='points-table-wrapper']//tr["+i+"]//td[1]")).getText();
            String points = driver.findElement(By.xpath("//div[@class='points-table-wrapper']//tr["+i+"]//td[8]")).getText();
            System.out.print("teamName:" + teamName + " | points:" + points);
            System.out.println("");
        }
    }

    @Test
    public void iFramesConcepts() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/iframes.html");
        element = driver.findElement(By.cssSelector("#iframe1"));

            driver.switchTo().frame(element);
            Thread.sleep(2000);


        String button = driver.findElement(By.id("imageButton")).getText();
        System.out.println(button);

    }

    @Test
    public void iFrameTest() throws InterruptedException {

                driver.get("https://www.selenium.dev/selenium/web/iframes.html");

                WebElement iframe = driver.findElement(By.id("iframe1"));
                Thread.sleep(2000);
                driver.switchTo().frame(iframe);

                assertEquals(true, driver.getPageSource().contains("We Leave From Here"));


                WebElement emailE = driver.findElement(By.id("email"));
                emailE.sendKeys("admin@selenium.dev");
                emailE.clear();
                driver.switchTo().defaultContent();


                //switch To IFrame using name or id
                WebElement iframe1=driver.findElement(By.name("iframe1-name"));
                driver.switchTo().frame(iframe1);
                assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
                WebElement email = driver.findElement(By.id("email"));
                email.sendKeys("admin@selenium.dev");
                email.clear();
                driver.switchTo().defaultContent();


                //switch To IFrame using index
                driver.switchTo().frame(0);
                assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
                driver.switchTo().defaultContent();
                assertEquals(true, driver.getPageSource().contains("This page has iframes"));
            }
    }

