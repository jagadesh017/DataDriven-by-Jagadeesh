package pages;

import common.CommonLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class FacebookLoginPage extends CommonLib {
    WebDriver driver;

    public FacebookLoginPage(WebDriver driver) {
      super(driver);
        this. driver = driver;
        PageFactory.initElements(driver, this);
    }

    //-----------------locators--------------
    @FindBy(css = "#_R_oiqjbjb9pb6amH1_")
    private WebElement userName;

    @FindBy(css  = "input[type='password']")
    private WebElement pwd;

    @FindBy(css = "div[aria-label='Log in']")
    private WebElement loginCTA;

    //----------------action methods--------------

    public void enterUsername(String username)  {
        userName.sendKeys(username);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void enterPassword(String pass)  {
        pwd.sendKeys(pass);
    }

    public void clickSignIn(){
        loginCTA.click();

    }
}
