package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
                for( int j = 1; j <= courseTableColumnCount.size(); j++) {
                    String courseName = driver.findElement(By.xpath("//table[@name='courses']//tr["+i+"]//td[2]")).getText();
                    String coursePrice = driver.findElement(By.xpath("//table[@name='courses']//tr["+i+"]//td[3]")).getText();

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
                 String amount = driver.findElement(By.xpath("//table[@id='product' and not(@name='courses')]//tr["+i+"]//td[4]")).getText();
                    sum += Integer.parseInt(amount);
             }
             System.out.println("the total sum is " + sum);
         } catch (Exception e) {
                 throw new RuntimeException(e);
         }
         return this;
     }


 }
