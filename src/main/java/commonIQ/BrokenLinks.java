package commonIQ;

import common.CommonLib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLinks extends CommonLib {
    public WebDriver driver;

    public BrokenLinks(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BrokenLinks validateBrokenLinks() {
        try {
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            Reporter.log("Title: " + title);
            Reporter.log("URL: " + url);
            Reporter.log("method to validate broken links");

            List<WebElement> links = driver.findElements(By.tagName("a"));
            System.out.println(links.size());
            for (WebElement link : links) {
                String urls = link.getAttribute("href");

                HttpsURLConnection httpURLConnection = (HttpsURLConnection) new URL(urls).openConnection();
                httpURLConnection.connect();
                int code = httpURLConnection.getResponseCode();
                if (code >= 400) {
                    Reporter.log("Broken link: " + urls + " - Response code: " + code);
                }else{
                   // Reporter.log("Valid link: " + urls + " - Response code: " + code);
                    System.out.println("Valid link: " + urls + " - Response code: " + code);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
