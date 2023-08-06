package Datadriventest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basepackage.Baseclass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class search extends Baseclass{
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		String url=prod.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	@Test(dataProvider="getdata")
	public void searchmethod(String userid,String password,String status) throws InterruptedException {
		System.out.println("search class---searctest method ---"+Thread.currentThread().getId());
		if(status.equals("Pass")) {
			String[] li= {"HP LP3065","Apple"};
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.id("input-email")).sendKeys(userid);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		for(int k=0;k<li.length;k++) {
		driver.findElement(By.xpath("//input[@name='search']")).clear();
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(li[k]);
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		driver.findElement(By.xpath("//div[@class='product-thumb']//button[@data-toggle='tooltip'][1]")).click();
		WebElement we=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']//a[1]"));
		System.out.println(we.getText()+"web");
		System.out.println(li[k]+"list");
		Assert.assertTrue(we.getText().contains(li[k]));
	}}}
		@DataProvider(name="getdata")
		public Object[][]  getdata() throws IOException {
			String path=System.getProperty("user.dir")+"\\Datafolder\\Dataforselrealtime.xlsx";
			FileInputStream fi=new FileInputStream(path);
			XSSFWorkbook book=new XSSFWorkbook(fi);
			XSSFSheet sheet =book.getSheet("Sheet1");
			int rownum=sheet.getLastRowNum();
			Object[][] obj=new Object[rownum][3];
			for(int i=0;i<rownum;i++) {
				Row row=sheet.getRow(i+1);
				obj[i][0]=row.getCell(2).getStringCellValue();
				obj[i][1]=row.getCell(4).getStringCellValue();	
				obj[i][2]=row.getCell(6).getStringCellValue();
				}
			book.close();
			return obj;
			}
	
		
	}


