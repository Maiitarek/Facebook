package PageObjects;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

 public class loginPO {

WebDriver driver;
    public WebElement createUser = driver.findElement(By.xpath("//div[@class='_6ltg']/a"));
    public WebElement firstName = driver.findElement(By.xpath("//div[text()='First name']"));
    public WebElement lastName = driver.findElement(By.xpath("//div[text()='Surname']"));
    public WebElement email = driver.findElement(By.xpath("//input[@name='reg_email__']"));
    public WebElement password = driver.findElement(By.xpath("//input[@id='password_step_input']"));
    public WebElement birthYear = driver.findElement(By.xpath("//select[@id='year']/option[@value='1995']"));
    public WebElement gender = driver.findElement(By.xpath("//input[@id='u_3_2_zl']"));
    public WebElement signUp = driver.findElement(By.xpath("//div[@class='_1lch']/button[@name]"));
    public WebElement emailCode = driver.findElement(By.xpath("//div[@class='_8n2n _8na1']/input[@id='code_in_cliff']"));




}
