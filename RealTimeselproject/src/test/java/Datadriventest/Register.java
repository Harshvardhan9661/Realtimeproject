package Datadriventest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.math3.analysis.function.Ceil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import basepackage.Baseclass;
import io.github.bonigarcia.wdm.WebDriverManager;
import utlity.realtime.Listenerclass;

@Listeners({Listenerclass.class})
public class Register extends Baseclass{
	public static WebDriver driver;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		String url=prod.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();}
	@AfterMethod
	public void teardown() {
		driver.close();
	}
	
	@Test(dataProvider ="getdata",retryAnalyzer=utlity.realtime.Retrylisteners.class)
	public void Registermethod( String First_Name ,String LastName , String userid,Integer Telephone ,String password ,String Confirm_password ,String status) {
		System.out.println("register class---searctest method ---"+Thread.currentThread().getId());
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("input-firstname")).sendKeys(First_Name.toString());
		driver.findElement(By.id("input-lastname")).sendKeys(LastName.toString());
		driver.findElement(By.id("input-email")).sendKeys(userid.toString());
		driver.findElement(By.id("input-telephone")).sendKeys(Telephone.toString());
		driver.findElement(By.id("input-password")).sendKeys(password.toString());
		driver.findElement(By.id("input-confirm")).sendKeys(Confirm_password.toString());
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String message=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertTrue(message.contains("Your Account Has Been Created!"));
		
		}
	@DataProvider(name="getdata")
	public Object[][] getdata() throws IOException {
		String path=System.getProperty("user.dir")+"\\Datafolder\\Dataforselrealtime.xlsx";
		FileInputStream fi=new FileInputStream(path);
		XSSFWorkbook book=new XSSFWorkbook(fi);
		XSSFSheet sheet =book.getSheet("Sheet1");
		int rownum=sheet.getLastRowNum();
		int cellnum=sheet.getRow(1).getLastCellNum();
		Object[][] obj=new Object[rownum][cellnum];
		for(int i=0;i<rownum;i++) {
			Row row=sheet.getRow(i+1);
			for(int j=0;j<cellnum;j++) {
				Cell cell=row.getCell(j);
				CellType type=cell.getCellType();
				switch(type) {
				case STRING : obj[i][j]=row.getCell(j).getStringCellValue();break;
				case NUMERIC : obj[i][j]=(int)row.getCell(j).getNumericCellValue();break;}
		}}
		return obj;
	}}
