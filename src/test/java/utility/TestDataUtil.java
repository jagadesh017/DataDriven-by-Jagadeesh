package utility;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
public class TestDataUtil {

    @DataProvider(name ="basic")
    public Object[][] getTestData() {
        return new Object[][]{
                {"testuser1", "testpassword1"},
                {"testuser2", "testpassword2"},
                {"testuser3", "testpassword3"}
        };
    }
    @DataProvider (name = "excelData")
    public Object[][] getDataFromExcel() {
        Object [][]data = null;

        try {
            FileInputStream fis = new FileInputStream("./TestData/TestData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            data = new Object[rowCount-1][colCount];

            for(int i=0;i<rowCount-1;i++) {
                XSSFRow row = sheet.getRow(i+1);
                for(int j=0;j<colCount;j++) {
                    data[i][j] = row.getCell(j).getStringCellValue();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}