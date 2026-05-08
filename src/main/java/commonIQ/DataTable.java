package commonIQ;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class DataTable {
    WebDriver driver;
    public DataTable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//table[@id='example']//tr")
    private List<WebElement> rowCount;

    public DataTable webTableAgeCalculation() {
        try{
            int sum = 0;
            for(int i = 1; i < rowCount.size()-1; i++){
                rowCount.get(i).getText();
                   String age= driver.findElement(By.xpath("//table[@id='example']//tr["+i+"]//td[4]")).getText();
                   sum += Integer.parseInt(age);
            }
            System.out.println("Total age is: " + sum);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    return this;
    }

    public DataTable printAllOfficeNames() {
        try{
            for(int i = 1; i < rowCount.size()-1; i++){
                String name=driver.findElement(By.xpath("//table[@id='example']//tr["+i+"]//td[1]")).getText();
                String officeNames = driver.findElement(By.xpath("//table[@id='example']//tr["+i+"]//td[3]")).getText();
                System.out.println("Name is : " + name +" and Office name is: " + officeNames);
            }
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
        return this;
    }
}
