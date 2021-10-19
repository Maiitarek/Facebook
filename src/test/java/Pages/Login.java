package Pages;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.TestNG;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Login {
    WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;

    @BeforeTest
        public void Initialization(){
        // To set the path of the Chrome driver.
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\java\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        // To launch facebook
        driver.get("http://www.facebook.com/");
        // To maximize the browser
        driver.manage().window().maximize();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
        public void FbLoginLogout() throws IOException, InterruptedException {
        // Import Excel sheet.
        File src=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData\\users.xlsx");
        // Load the file.
        FileInputStream fis = new FileInputStream(src);
        // Load he workbook.
        workbook = new XSSFWorkbook(fis);
        // Load the sheet in which data is stored.
        sheet= workbook.getSheetAt(0);
        for(int i=0; i<=sheet.getLastRowNum(); i++){
				/*I have added test data in the cell A2 as "testemailone@test.com" and B2 as "password"
				Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on
				and first column (A) as 0 and second column (B) as 1 and so on*/

            // Import data for Email.
            cell = sheet.getRow(i).getCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//div[@class='_6lux']/input[@id='email']")).clear();
            driver.findElement(By.xpath("//div[@class='_6lux']/input[@id='email']")).sendKeys(cell.getStringCellValue());

            // Import data for password.
            cell = sheet.getRow(i).getCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//div[@class='_6lux']//input[@id='pass']")).clear();
            driver.findElement(By.xpath("//div[@class='_6lux']//input[@id='pass']")).sendKeys(cell.getStringCellValue());
            // To know if user is valid
            //cell =sheet.getRow(i).getCell()

            // To click on Login button
            driver.findElement(By.xpath("//button[@name='login']")).click();
            Thread.sleep(10000);
            System.out.println(sheet.getRow(i).getCell(2).getStringCellValue());
            if(sheet.getRow(i).getCell(2).getStringCellValue()== "valid") {
                //Assert user logged in successfully
                Assert.assertTrue(driver.findElement(By.xpath("//div[@class='j83agx80 l9j0dhe7']")).isDisplayed(), "Invalid user credentials");

                //To click on Account settings dropdown
            } else if(sheet.getRow(i).getCell(2).getStringCellValue()== "invalid") {

                Assert.assertTrue(driver.findElement(By.xpath("//div[@class='_9ay7']")).isDisplayed(), "Invalid user credentials");

            }
            driver.findElement(By.xpath("//div[@class='j83agx80 l9j0dhe7']")).click();
            // To click on logout button
            driver.findElement(By.xpath("//text()[.='Log Out']/ancestor::span[1]")).click();

        }
    }

}
