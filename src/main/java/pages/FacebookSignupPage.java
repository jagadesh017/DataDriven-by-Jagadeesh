package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class FacebookSignupPage extends CommonLib{
    public FacebookSignupPage(WebDriver driver) {
        super(driver);
    }


    // locators

    @FindBy(css = "#_R_1cl2p4jikacppb6amH1_")
    private WebElement firstNameField;

    @FindBy(css = "#_R_1kl2p4jikacppb6amH1_")
    private WebElement lastNameField;

    @FindBy(id = "_r_9_")
    private WebElement month;

    @FindBy(xpath = "//*[text()='Create new account']")
    private WebElement createNewAccount;

    public void clickSignUP() throws IOException {
        createNewAccount.click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastNAme(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterMobileNumber(String months) throws IOException {
        month.sendKeys(months);
    }
}
