package Datadriventest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basepackage.Baseclass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login extends Baseclass {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		String url1=prod.getProperty("url");
		driver.get(url1);
		driver.manage().window().maximize();}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	@Test(dataProvider ="getdata")
	public void loginmethod( String userid ,String password) {
		System.out.println("login class---searctest method ---"+Thread.currentThread().getId());
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.id("input-email")).sendKeys(userid);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Edit your account information']")).isDisplayed());
	}
	@DataProvider(name="getdata")
	public Object[][] getdata() throws IOException {
		String path=System.getProperty("user.dir")+"\\Datafolder\\Dataforselrealtime.xlsx";
		FileInputStream fi=new FileInputStream(path);
		XSSFWorkbook book=new XSSFWorkbook(fi);
		XSSFSheet sheet =book.getSheet("Sheet1");
		int rownum=sheet.getLastRowNum();
		Object[][] obj=new Object[rownum][2];
		for(int i=0;i<rownum;i++) {
			Row row=sheet.getRow(i+1);
			obj[i][0]=row.getCell(2).getStringCellValue();
			obj[i][1]=row.getCell(4).getStringCellValue();						
			}
		book.close();
		return obj;
		}
	}