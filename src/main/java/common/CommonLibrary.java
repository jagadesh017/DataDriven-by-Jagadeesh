package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonLibrary extends CommonLib{

    WebDriver driver;
    public CommonLibrary(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public abstract void verifyPageTitle();

    public abstract boolean verifyPageTitle(String expectedTitle);

    public void enterText(WebElement element, String text) {
     element.sendKeys(text);
    }

    public void enterText(WebElement element, int value) {
        element.sendKeys(String.valueOf(value));
    }

}
