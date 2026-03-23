package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Alert;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class CommonLib {
    WebDriver driver;
    public  CommonLib(WebDriver driver) {
    this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyPageUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(expectedUrl);
    }

    public boolean verifyPageUrlContains(String expectedUrlPart) {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains(expectedUrlPart);
    }

    public boolean verifyPageTitle(String expectedTitle) {
        String currentTitle = driver.getTitle();
        return currentTitle.equals(expectedTitle);
    }

    public boolean verifyPageTitleContains(String expectedTitlePart) {
        String currentTitle = driver.getTitle();
        return currentTitle.contains(expectedTitlePart);
    }

    public List<String> getDropdownValues(WebElement dropdownElement) {
        List<String> dropdownValues = new ArrayList<>();
        Select select = new Select(dropdownElement);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            dropdownValues.add(option.getText());
        }
        return dropdownValues;
    }

    public boolean verifyDropdownValueExists(WebElement dropdownElement, String expectedValue) {
        List<String> dropdownValues = getDropdownValues(dropdownElement);
        return dropdownValues.contains(expectedValue);
    }

    public boolean verifyDropdownSelectedValue(WebElement dropdownElement, String expectedSelectedValue) {
        Select select = new Select(dropdownElement);
        String selectedValue = select.getFirstSelectedOption().getText();
        return selectedValue.equals(expectedSelectedValue);
    }

    public boolean selectDropdownValue(WebElement dropdownElement, String valueToSelect) {
        try {
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(valueToSelect);
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting dropdown value: " + e.getMessage());
            return false;
        }
    }

    public boolean selectDropdownByValue(WebElement dropdownElement, String value) {
        try {
            Select select = new Select(dropdownElement);
            select.selectByValue(value);
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting dropdown value: " + e.getMessage());
            return false;
        }
    }

    public boolean selectDropdownByIndex(WebElement dropdownElement, int index) {
        try {
            Select select = new Select(dropdownElement);
            select.selectByIndex(index);
            return true;
        } catch (Exception e) {
            System.out.println("Error selecting dropdown value: " + e.getMessage());
            return false;
        }
    }

    public boolean mouseOver(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Error performing mouse over: " + e.getMessage());
            return false;
        }
    }

    public boolean mouseOverAndClick(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            return true;
        } catch (Exception e) {
            System.out.println("Error performing mouse over and click: " + e.getMessage());
            return false;
        }
    }

    public boolean doubleClick(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Error performing double click: " + e.getMessage());
            return false;
        }
    }

    public boolean rightClick(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Error performing right click: " + e.getMessage());
            return false;
        }
    }

    public boolean dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDrop(sourceElement, targetElement).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Error performing drag and drop: " + e.getMessage());
            return false;
        }
    }

    public boolean mouseOverWithDelay(WebElement element, long delayMilliseconds) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            return true;
        } catch (Exception e) {
            System.out.println("Error performing mouse over with delay: " + e.getMessage());
            return false;
        }
    }

    public boolean takeScreenshot(String filePath) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot taken successfully at: " + filePath);
            return true;
        } catch (IOException e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
            return false;
        }
    }

    public boolean takeElementScreenshot(WebElement element, String filePath) {
        try {
            File source = element.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);
            System.out.println("Element screenshot taken successfully at: " + filePath);
            return true;
        } catch (IOException e) {
            System.out.println("Error taking element screenshot: " + e.getMessage());
            return false;
        }
    }

    public String takeScreenshotWithTimestamp(String directoryPath) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = System.currentTimeMillis() + "";
            String fileName = "screenshot_" + timestamp + ".png";
            String filePath = directoryPath + File.separator + fileName;
            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot with timestamp taken successfully at: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("Error taking screenshot with timestamp: " + e.getMessage());
            return null;
        }
    }

    public String takeScreenshotAsBase64() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
            System.out.println("Screenshot captured as Base64");
            return base64Screenshot;
        } catch (Exception e) {
            System.out.println("Error taking Base64 screenshot: " + e.getMessage());
            return null;
        }
    }


    public boolean acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            System.out.println("Alert accepted successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error accepting alert: " + e.getMessage());
            return false;
        }
    }

    public boolean dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            System.out.println("Alert dismissed successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error dismissing alert: " + e.getMessage());
            return false;
        }
    }

    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            return alertText;
        } catch (Exception e) {
            System.out.println("Error getting alert text: " + e.getMessage());
            return null;
        }
    }

    public boolean typeInAlert(String text) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            System.out.println("Text typed in alert: " + text);
            return true;
        } catch (Exception e) {
            System.out.println("Error typing in alert: " + e.getMessage());
            return false;
        }
    }

    public boolean acceptAlertWithWait(int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Alert accepted successfully after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error accepting alert with wait: " + e.getMessage());
            return false;
        }
    }

    public boolean dismissAlertWithWait(int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            System.out.println("Alert dismissed successfully after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error dismissing alert with wait: " + e.getMessage());
            return false;
        }
    }

    public String getAlertTextWithWait(int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            return alertText;
        } catch (Exception e) {
            System.out.println("Error getting alert text with wait: " + e.getMessage());
            return null;
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean typeAndAcceptAlert(String text) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            alert.accept();
            System.out.println("Text typed and alert accepted: " + text);
            return true;
        } catch (Exception e) {
            System.out.println("Error typing and accepting alert: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForElementVisible(WebElement element, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("Element is visible after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for element visibility: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForElementPresent(org.openqa.selenium.By locator, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("Element is present in DOM after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for element presence: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForElementClickable(WebElement element, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("Element is clickable after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for element clickable: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForElementInvisible(WebElement element, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.invisibilityOf(element));
            System.out.println("Element is invisible after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for element invisibility: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForElementText(WebElement element, String text, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            System.out.println("Element text '" + text + "' found after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for element text: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForNumberOfElements(org.openqa.selenium.By locator, int count, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, count));
            System.out.println("Number of elements (" + count + ") found after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for number of elements: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForUrlContains(String urlPart, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.urlContains(urlPart));
            System.out.println("URL contains '" + urlPart + "' after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for URL: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForPageTitle(String titlePart, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.titleContains(titlePart));
            System.out.println("Page title contains '" + titlePart + "' after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for page title: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForElementSelected(WebElement element, int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
            System.out.println("Element is selected after waiting");
            return true;
        } catch (Exception e) {
            System.out.println("Error waiting for element selection: " + e.getMessage());
            return false;
        }
    }

    public long getElementWaitTime(WebElement element, int maxWaitSeconds) {
        try {
            long startTime = System.currentTimeMillis();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            long endTime = System.currentTimeMillis();
            long waitTime = endTime - startTime;
            System.out.println("Element wait time: " + waitTime + " milliseconds");
            return waitTime;
        } catch (Exception e) {
            System.out.println("Error getting element wait time: " + e.getMessage());
            return -1;
        }
    }

}
