package Pages;
import PageObjects.loginPO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class SignUp  {


    WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;

    @BeforeTest
    public void Initialization() {
        // To set the path of the Chrome driver.
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        // To launch facebook
        driver.get("http://www.facebook.com/");
        // To maximize the browser
        driver.manage().window().maximize();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void FbSignUp() throws IOException, InterruptedException {
        // Import Excel sheet.
        File src=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData\\SignUpData.xlsx");
        // Load the file.
        FileInputStream fis = new FileInputStream(src);
        // Load he workbook.
        workbook = new XSSFWorkbook(fis);
        // Load the sheet in which data is stored.
        sheet= workbook.getSheetAt(0);
        for(int i=0; i<=sheet.getLastRowNum(); i++){


            // Click on create new account
			driver.findElement(By.xpath("//div[@class='_6ltg']/a")).click();

            // Import data for FirstName.
            cell = sheet.getRow(i).getCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(cell.getStringCellValue());
            // Import data for LastName.
            cell = sheet.getRow(i).getCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(cell.getStringCellValue());
            // Import data for email.
            cell = sheet.getRow(i).getCell(2);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys(cell.getStringCellValue());

            WebDriverWait wait = new WebDriverWait((WebDriver) driver.findElement(By.xpath("//div[@class='mbm _a4y']//input[@id='u_3_j_Tk']")), 10);
            // Import data for confirm email.
            cell = sheet.getRow(i).getCell(3);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//div[@class='mbm _a4y']//input[@id='u_3_j_Tk']")).sendKeys(cell.getStringCellValue());

            // Import data for password.
            cell = sheet.getRow(i).getCell(4);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath(("//input[@id='password_step_input']"))).sendKeys(cell.getStringCellValue());


            // Import data for birthYear.
            cell = sheet.getRow(i).getCell(5);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath(String.valueOf("//select[@id='year']/option[@value='1995']"))).click();

            // click gender.
            driver.findElement(By.xpath("//input[@id='u_3_2_zl']")).click();


            // To click on signUp button
            driver.findElement(By.xpath("//div[@class='_1lch']/button[@name]")).click();
            //Assert user registered successfully
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='_8n2n _8na1']/input[@id='code_in_cliff']")).isDisplayed(), "unable to register user");

        }

    }
}


