package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class FacebookLoginPage extends CommonLib{
    WebDriver driver;

    public FacebookLoginPage(WebDriver driver) {
      super(driver);
        this. driver = driver;
        PageFactory.initElements(driver, this);
    }

    //locators
    @FindBy(css = "#_R_1h6kqsqppb6amH1_")
    private WebElement userName;

    @FindBy(css  = "input[type='password']")
    private WebElement pwd;

    @FindBy(css = "div[aria-label='Log in']")
    private WebElement loginCTA;

    //action methods

    public void enterUsername(String username)  {
        userName.sendKeys(username);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void enterPassword(String pass)  {
        pwd.sendKeys(pass);
    }

    public void clickSignin(){
        loginCTA.click();

    }
}
