package pages;

import common.CommonLib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CrexPage extends CommonLib {
    public WebDriver driver;

    public CrexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    // ------------------ page locators ------------------
    @FindBy(xpath = "//h3[@class='match-number']//span[contains(text(),'Wankhede Stadium, Mumbai')]")
    private WebElement wankhedeStadium;

    @FindBy(xpath = "/html/body/app-root/div/app-match-details/div[3]/div/app-match-scorecard/div/div[1]/div[2]/div[2]/div/app-scorecard-table/div/table")
    private WebElement scoreTable;

    @FindBy(xpath = "/html/body/app-root/div/app-match-details/div[3]/div/app-match-scorecard/div/div[1]/div[2]/div[2]/div/app-scorecard-table/div/table//tr")
    private List<WebElement> scoreTableRows;

    @FindBy(css = "a[title='Table']")
    private List<WebElement> table;

    @FindBy(xpath = "//div[@class='points-table-wrapper']//table//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//div[@class='points-table-wrapper']//table//tr[2]//td")
    private List<WebElement> tableCells;

    @FindBy(xpath = "//*[@id='slide']/app-live-matches/div[1]/div[1]/div/a/div/div[1]")
    private WebElement liveMatch;

    @FindBy(xpath = "//*[text()=' Points Table ']")
    private WebElement tableTab;

    //---------------action methods----------------

    public CrexPage checkRohitScoreFromTheTable(){
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

    public CrexPage getIPL2026Table() {
        try {
            liveMatch.click();
            Thread.sleep(1000);
            tableTab.click();
            Thread.sleep(2000);
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