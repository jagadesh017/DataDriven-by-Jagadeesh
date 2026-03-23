package testdatagenerators;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class DataGenerators {
    public WebDriver driver;

    @DataProvider(name = "static")
    public static Object[][] testDataGenerator() {

        Object[][] data = {{"user1", "pass1"}};
        return data;
    }

    @DataProvider(name = "Excel")
    public static Object[][] testDataGeneratorForLogin() throws Exception {

        FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int numberOfData = sheet.getPhysicalNumberOfRows();

        Object[][] testData = new Object[numberOfData][2];
        for (int i = 0; i < numberOfData; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell username = row.getCell(0);
            XSSFCell pass = row.getCell(1);
            testData[i][0] = username.getStringCellValue();
            testData[i][1] = pass.getStringCellValue();

        }
        return testData;
    }

    @DataProvider(name = "Register")
    public static Object[][] testDataGeneratorForRegister() throws Exception {

        FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet2");
        int numberOfData = sheet.getPhysicalNumberOfRows();

        Object[][] testData = new Object[numberOfData][3];
        for (int i = 0; i < numberOfData; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell firstName = row.getCell(0);
            XSSFCell lastName = row.getCell(1);
            XSSFCell phoneNum = row.getCell(1);

            testData[i][0] = firstName.getStringCellValue();
            testData[i][1] = lastName.getStringCellValue();
            testData[i][2] = phoneNum.getStringCellValue();
        }
        return testData;

    }

    // single data provider for multiple classes
    @DataProvider(name = "ExcelData")
    public static Object[][] testDataGeneratorForLoginPage(Method method) throws Exception {

        if (method.getName().equalsIgnoreCase("tc_001_login_functionality_3")) {

            FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int numberOfData = sheet.getPhysicalNumberOfRows();

            Object[][] testData = new Object[numberOfData][2];
            for (int i = 0; i < numberOfData; i++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell username = row.getCell(0);
                XSSFCell pass = row.getCell(1);
                testData[i][0] = username.getStringCellValue();
                testData[i][1] = pass.getStringCellValue();

            }
            return testData;

        } else if (method.getName().equalsIgnoreCase("tc_002_newUser_2")) {

            FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet2");
            int numberOfData = sheet.getPhysicalNumberOfRows();

            Object[][] testData = new Object[numberOfData][3];
            for (int i = 0; i < numberOfData; i++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell firstName = row.getCell(0);
                XSSFCell lastName = row.getCell(1);
                XSSFCell phoneNum = row.getCell(1);
                testData[i][0] = firstName.getStringCellValue();
                testData[i][1] = lastName.getStringCellValue();
                testData[i][2] = phoneNum.getStringCellValue();
            }
            return testData;

        } else {
            Object[][] testData = new Object[2][3];
            return testData;
        }
    }
}
