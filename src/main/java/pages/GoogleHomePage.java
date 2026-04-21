package pages;

import common.CommonLibrary;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage extends CommonLibrary {
    WebDriver driver;
    public GoogleHomePage (WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    //------------------------------------------- page locators -------------------------------------------

    @FindBy (css = "div#SIvCob")
    private WebElement languageOptions;

    @FindBy(css = "#APjFqb")
    private WebElement searchInputField;


    //------------------------------------------- page actions -------------------------------------------
    @Override
    public void verifyPageTitle() {
        try{
            String title = driver.getTitle();
            System.out.println(title);
        } catch (Exception e) {
            System.out.println("Error while getting page title: " + e.getMessage());
        }

    }

    @Override
    public boolean verifyPageTitle(String expectedTitle) {
        try {
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Page title verification passed: " + actualTitle);
                return true;
            } else {
                System.out.println("Page title verification failed. Expected: " + expectedTitle + ", but got: " + actualTitle);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error while verifying page title: " + e.getMessage());
        }
        return false;
    }

    public void enterTextInSearchField(String text) {
        try {
           super.enterText(searchInputField,text);
        } catch (Exception e) {
            System.out.println("Error while entering text in search field: " + e.getMessage());
        }
    }
    public void enterValueInSearchField(int value) {
        try {
            super.enterText(searchInputField, value);
        } catch (Exception e) {
            System.out.println("Error while entering value in search field: " + e.getMessage());
        }
    }

    public void enterSearchQuery(String query) {
        try {
            searchInputField.sendKeys(query);
            System.out.println("Entered search query: " + query);
        } catch (Exception e) {
            System.out.println("Error while entering search query: " + e.getMessage());
        }
    }

    // overriding
    public void enterText(WebElement element, String text) {
        try {
            element.clear();
            super.enterText(element, text);
            System.out.println("Entered text: " + text);
        } catch (Exception e) {
            System.out.println("Error while entering text: " + e.getMessage());
        }
    }

    public void clickEnter(){
        enterText(searchInputField, "hello");
        searchInputField.sendKeys(Keys.ENTER);
    }
    public boolean verifyPageUrlContains(String expectedUrlPart){

        return false;
    }
}
