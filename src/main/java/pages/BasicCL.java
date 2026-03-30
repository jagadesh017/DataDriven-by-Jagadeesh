package pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.BSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

 public class BasicCL extends CommonLib {
     WebDriver driver;

     public BasicCL(WebDriver driver) {
         this.driver = driver;
         PageFactory.initElements(driver, this);
     }

     @FindBy(id = "dropdown-class-example")
     private WebElement dropdown;

     @FindBy(id = "mousehover")
     private WebElement hoverbuttonCTAVal;

     @FindBy(css = "select option")
     private List<WebElement> dropdownlist;

     @FindBy(css = ".mouse-hover-content a")
     private List<WebElement> mousehoverList;

     @FindBy(css = "#mousehover")
     private WebElement hoverButtonCTA;


     public BasicCL selectDropdownValueslist() {
         try {
             Select select = new Select(dropdown);
             List<WebElement> options = select.getOptions();
             for (WebElement option : options) {
                 System.out.println(option.getText());
             }
         } catch (Exception e) {
             Assert.fail("failed select dropdown values");
         }
         return this;
     }

     public BasicCL mousehovermethod() {
         try {
             Actions action = new Actions(driver);
             // action.moveToElement(hoverbutton).perform();
             //WebElement hoverButton = driver.findElement(By.id("mousehover"));
             action.moveToElement(hoverbuttonCTAVal).perform();

             for (WebElement element : mousehoverList) {
                 System.out.println(element.getText());
             }
         } catch (Exception e) {
             Assert.fail("failed mouse hover method");
         }
         return this;
     }

     public BasicCL printHoverMenuValues() {
         try {

             Actions actions = new Actions(driver);
             WebElement hoverButton = driver.findElement(By.id("mousehover"));
             actions.moveToElement(hoverButton).perform();

             List<WebElement> options = driver.findElements(By.cssSelector(".mouse-hover-content a"));

             for (WebElement option : options) {
                 System.out.println("Hover Option: " + option.getText());
             }
         } catch (Exception e) {
             Assert.fail("Could not retrieve hover menu values: " + e.getMessage());
         }
         return this;
     }

     @FindBy(xpath = "//table[@name='courses']//tr")
     private List<WebElement> courseTableRowsCount;
     @FindBy(xpath = "//table[@name='courses']//td")
     private List<WebElement> courseTableColumnCount;

     public BasicCL priceOfTheCourse() {
         try {
             for (int i = 2; i <= courseTableRowsCount.size(); i++) {
                 for (int j = 1; j <= courseTableColumnCount.size(); j++) {
                     String courseName = driver.findElement(By.xpath("//table[@name='courses']//tr[" + i + "]//td[2]")).getText();
                     String coursePrice = driver.findElement(By.xpath("//table[@name='courses']//tr[" + i + "]//td[3]")).getText();

                     if (courseName.contains("Selenium Automation")) {
                         System.out.println("✅ Found! Course: " + courseName + " | Price: " + coursePrice);
                         break;
                     }

                 }
             }
         } catch (Exception e) {
             Assert.fail("Failed to get the price of the course: " + e.getMessage());
         }
         return this;
     }
//----------------

     @FindBy(xpath = "//table[@id='product' and not(@name='courses')]//tr")
     private List<WebElement> productTableRowsCount;


     public BasicCL citiesAndTheirSum() {
         try {
             int sum = 0;
             for (int i = 1; i < productTableRowsCount.size(); i++) {
                 String amount = driver.findElement(By.xpath("//table[@id='product' and not(@name='courses')]//tr[" + i + "]//td[4]")).getText();
                 sum += Integer.parseInt(amount);
             }
             System.out.println("the total sum is " + sum);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
         return this;
     }

     //alerts
     @FindBy(id = "confirmbtn")
     private WebElement alertConfirmCTA;

     public BasicCL getAlertMessage() {
         try {
             alertConfirmCTA.click();
             WebDriverWait wait = new WebDriverWait(driver, 10);

             wait.until(ExpectedConditions.alertIsPresent());
             Alert a = driver.switchTo().alert();
             String text = a.getText();
             System.out.println("the alert message is " + text);

         } catch (Exception e) {
             Assert.fail("failed to get allert message");
         }
         return this;
     }
    @FindBy(css = "#openwindow")
    private WebElement openWindow;
     public BasicCL switchWindowFromParentToChild() {
         try {
             openWindow.click();
             Set<String>windows=driver.getWindowHandles();
                for(String window:windows) {
                    driver.switchTo().window(window);
                }
                String childWindowTitle = driver.getTitle();
                System.out.println("the child window title is " + childWindowTitle);
                driver.switchTo().parentFrame();
                String parentWindowTitle = driver.getTitle();
                System.out.println("the parent window title is " + parentWindowTitle);

         } catch (Exception e) {
             Assert.fail("failed to switch window");
         }
         return this;
     }
     @FindBy(css = "input#autocomplete")
     private WebElement autocomplete;

     @FindBy(css = "li.ui-menu-item")
     private List<WebElement> menuitem;

     public BasicCL autoSuggestionCapture() {
         try {
            autocomplete.sendKeys("ind");
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfAllElements(menuitem));
            for (WebElement element : menuitem) {
                System.out.println("the auto suggestion values are " + element.getText());
                if(element.getText().equalsIgnoreCase("india")) {
                    element.click();
                    Thread.sleep(2000);
                    break;
                }
            }

         } catch (Exception e) {
             Assert.fail("failed to capture auto suggestion");
         }
         return this;
     }
        @FindBy(xpath = "//a[text()='VIEW ALL COURSES']")
        private WebElement allCoursesText;
     public BasicCL iframe() {
         try {
             driver.switchTo().frame(driver.findElement(By.id("courses-iframe")));
             Thread.sleep(2000);
             WebDriverWait wait = new WebDriverWait(driver, 10);
             wait.until(ExpectedConditions.visibilityOf(allCoursesText));
             System.out.println("the text in iframe is: " + allCoursesText.getText());
              //driver.switchTo().defaultContent();
         }catch (Exception e) {
             Assert.fail("failed to capture iframe");
         }
    return this;
     }
     @FindBy(css = "span.onoffswitch4-inner")
     private WebElement dollerSymbol;
     public BasicCL checkDoller() {
         try {
             iframe();
             Thread.sleep(2000);
            dollerSymbol.click();
             WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOf(dollerSymbol));
                System.out.println("the text in iframe is: " + dollerSymbol.getText());
         }catch (Exception e) {
             Assert.fail("failed to capture iframe");
         }
         return this;
     }

    //switching tab
    @FindBy(xpath = "//a[text()='Open Tab']")
     private WebElement openNewTab;

     public BasicCL openNewTab() {
         try {
             openNewTab.click();
             String handle = driver.getWindowHandle();

             for(String window:driver.getWindowHandles()) {
                 if(!window.equals(handle)) {
                     driver.switchTo().window(window);
                 }
             }
             System.out.println( driver.getCurrentUrl() +" and "+ driver.getTitle());
             Thread.sleep(2000);
         }catch (Exception e) {
             Assert.fail("failed to navigate to new Tab");
         }
     return this;
     }

     @FindBy(xpath = "//h3[@class='match-number']//span[contains(text(),'Wankhede Stadium, Mumbai')]")
     private WebElement wankhedeStadium;

     @FindBy(xpath = "/html/body/app-root/div/app-match-details/div[3]/div/app-match-scorecard/div/div[1]/div[2]/div[2]/div/app-scorecard-table/div/table")
     private WebElement scoreTable;

     @FindBy(xpath = "/html/body/app-root/div/app-match-details/div[3]/div/app-match-scorecard/div/div[1]/div[2]/div[2]/div/app-scorecard-table/div/table//tr")
     private List<WebElement> scoreTableRows;

     public BasicCL checkRohitScoreFromTheTable(){
         try {
             wankhedeStadium.click();
             Thread.sleep(4000);
             WebElement rohitRow = driver.findElement(By.xpath("/html/body/app-root/div/app-match-details/div[3]/div/app-match-scorecard/div/div[1]/div[2]/div[2]/div/app-scorecard-table/div/table//tr[2]"));

             List<WebElement> cells = rohitRow.findElements(By.xpath("/html/body/app-root/div/app-match-details/div[3]/div/app-match-scorecard/div/div[1]/div[2]/div[2]/div/app-scorecard-table/div/table//tr[2]//td"));

             System.out.println("Rohit Sharma Row Data:");
             for (WebElement cell : cells) {
                 String text = cell.getText().trim();
                     System.out.print(text + " | ");
             }

         } catch (Exception e) {
             Assert.fail("failed to get the score of rohit sharma in 1st IPL match");
         }
     return this;
     }

     @FindBy(css = "a[title='Table']")
     private List<WebElement> table;

     @FindBy(xpath = "//div[@class='points-table-wrapper']//table//tr")
     private List<WebElement> tableRows;

     @FindBy(xpath = "//div[@class='points-table-wrapper']//table//tr[2]//td")
     private List<WebElement> tableCells;

     public BasicCL getTable() {
         try {
             for (int i = 1; i < tableRows.size(); i++) {
                     tableRows.get(i).findElement(By.tagName("td"));
                     List<WebElement> cells = tableRows.get(i).findElements(By.tagName("td"));
                         for (WebElement cell : cells) {
                             StringBuilder stringBuilder = new StringBuilder();
                             String text = cell.getText().trim();
                             stringBuilder.append(text).append(" | ");
                             System.out.print(stringBuilder.toString());
                         }
                         System.out.println();
                     }
         }catch (Exception e) {
             Assert.fail("failed to print the table");
         }
         return this;
     }
 }
