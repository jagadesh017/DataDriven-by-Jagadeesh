package pages;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FacebookSignupPage extends CommonLib {
    public FacebookSignupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(css = "#_R_1cl2p4jikacppb6amH1_")
    private WebElement firstNameField;

    @FindBy(css = "#_R_1kl2p4jikacppb6amH1_")
    private WebElement lastNameField;

    @FindBy(id = "_r_9_")
    private WebElement monthSection;

    @FindBy(xpath = "//*[text()='Create new account']")
    private WebElement createNewAccount;

    @FindBy(css = "#_r_a_ div")
    private List<WebElement> monthsList;

    @FindBy(css = "[aria-label='Meta logo']")
    private WebElement metaLogo;

    public void clickSignUP() {
        createNewAccount.click();
    }

    public void enterFirstName(String firstName) {

        firstNameField.sendKeys(firstName);
    }

    public void enterLastNAme(String lastName) {

        lastNameField.sendKeys(lastName);
    }

    public void getDropdownOptions(String month) {
        List<String> options = new ArrayList<>();
        for (WebElement option : monthsList) {
            options.add(String.valueOf(option.getText().equals(month)));
        }
    }

    public FacebookSignupPage verifyMetaTag() {

        try {
            if (metaLogo.isDisplayed()) {
                System.out.println("Meta logo is displayed");
            }
        } catch (Exception e) {
            Assert.fail("Meta logo is not displayed");
        }
        return this;
    }

    public void getMetaText() {
        String metaText = metaLogo.getText();
        System.out.println("Meta text: " + metaText);
    }

}
