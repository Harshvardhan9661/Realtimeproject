package utlity.realtime;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listenerclass  extends Capturescreesnhot implements ITestListener{
	

	public void onTestStart(ITestResult result) {
		String name=result.getName();
		System.out.println(name+"test is starting");
		
	}

	public void onTestSuccess(ITestResult result) {
		String name=result.getName();
		System.out.println(name+" test is success");
	
	}

	public void onTestFailure(ITestResult result) {
		
		String name=result.getName();
		System.out.println(name+" test is failure");
		WebDriver driver = null ;
		
		try {
			System.out.println("inside try catch");
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		}
		catch(Exception e){
			e.getStackTrace();
		}
		try {
			Capturescreesnhot(driver,name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String name=result.getName();
		System.out.println(name+" test is skipped");

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		String name=result.getName();
		System.out.println(name+" test is failed with timeout");
	
	}

	public void onStart(ITestResult result) {
		String name=result.getName();
		System.out.println(name+" class is starting");
	
	}

	public void onFinish(ITestResult result) {
		String name=result.getName();
		System.out.println(name+" class is finished");
	}
	

}
